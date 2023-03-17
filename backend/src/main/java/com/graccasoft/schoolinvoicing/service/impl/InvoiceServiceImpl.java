package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.dto.InvoiceDto;
import com.graccasoft.schoolinvoicing.dto.InvoiceDtoMapper;
import com.graccasoft.schoolinvoicing.model.Billable;
import com.graccasoft.schoolinvoicing.model.Invoice;
import com.graccasoft.schoolinvoicing.model.InvoiceItem;
import com.graccasoft.schoolinvoicing.model.Student;
import com.graccasoft.schoolinvoicing.repository.BillableRepository;
import com.graccasoft.schoolinvoicing.repository.InvoiceRepository;
import com.graccasoft.schoolinvoicing.repository.StudentRepository;
import com.graccasoft.schoolinvoicing.service.InvoiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceDtoMapper invoiceDtoMapper;
    private final BillableRepository billableRepository;
    private final StudentRepository studentRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceDtoMapper invoiceDtoMapper,
                              BillableRepository billableRepository,
                              StudentRepository studentRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceDtoMapper = invoiceDtoMapper;
        this.billableRepository = billableRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<InvoiceDto> getSchoolClassInvoices(Long schoolClassId) {

        return invoiceRepository.findBySchoolClass(schoolClassId)
                .stream()
                .map(invoiceDtoMapper)
                .toList();
    }


    @Override
    public List<InvoiceDto> getStudentInvoices(Long studentId) {
        return invoiceRepository.findByStudent(studentId)
                .stream()
                .map(invoiceDtoMapper)
                .toList();
    }

    @Override
    public InvoiceDto getInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(()->new EntityNotFoundException("Invoice not found"));
        return invoiceDtoMapper.apply( invoice );
    }

    @Override
    public void generateInvoiceForSchoolClass(Long schoolClassId, String invoiceTitle) {
        //get billableItems
        List<Billable> billableItems = billableRepository.getAllBySchoolClassId(schoolClassId);
        if( billableItems.size() == 0 ){
            return; //might want to throw an exception
        }

        //get students
        //todo what if there are more than 1000 students
        List<Student> students = studentRepository.findAllBySchoolClass(billableItems.get(0).getSchoolClass(), PageRequest.of(0,1000));

        persistInvoices(students, billableItems, invoiceTitle);
    }

    @Override
    public void generateInvoiceForStudent(Long studentId, String invoiceTitle) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new EntityNotFoundException("Student not found"));
        //get billableItems
        List<Billable> billableItems = billableRepository.getAllBySchoolClassId(student.getSchoolClass().getId());
        if( billableItems.size() == 0 ){
            return; //might want to throw an exception
        }
        Student[] students = new Student[]{student};
        persistInvoices(Arrays.asList(students), billableItems, invoiceTitle);
    }

    @Override
    public Invoice getLatestStudentInvoice(Long studentId) {
        return invoiceRepository.findFirstByStudentIdOrderByIdDesc(studentId).orElseThrow(() -> new EntityNotFoundException("No invoices for student")) ;
    }

    @Transactional
    //todo run as async, this might take time
    private void persistInvoices(List<Student> students,List<Billable> billableItems, String invoiceTitle){
        //create invoice for each student
        students.forEach( (student)->{
            Invoice invoice = new Invoice();
            invoice.setTitle(invoiceTitle);
            invoice.setStudent(student);

            BigDecimal invoiceTotal = BigDecimal.ZERO;
            //invoice items
            List<InvoiceItem> invoiceItems = new ArrayList<>();
            for(Billable billable: billableItems){
                InvoiceItem invoiceItem = new InvoiceItem();
                invoiceItem.setInvoice(invoice);
                invoiceItem.setBillable(billable);
                invoiceItem.setQuantity(1);
                invoiceItem.setUnitPrice(billable.getUnitPrice());

                invoiceItems.add( invoiceItem );
                invoiceTotal = invoiceTotal.add(billable.getUnitPrice());
            }

            invoice.setTotalAmount(invoiceTotal);
            invoice.setItems(invoiceItems);

            //persist invoice

            invoiceRepository.save(invoice);

        });
    }
}
