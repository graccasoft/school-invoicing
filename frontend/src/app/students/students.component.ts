import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { SchoolClass } from '../model/school-class';
import { Student } from '../model/student';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Payment } from '../model/payment';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent {

  schoolClass!: SchoolClass
  students!: Student[]
  student: Student = new Student()
  payment: Payment = new Payment()

  searchLastName: string = ""

  constructor(
    private apiService: ApiService,
    private route: ActivatedRoute,
    private toastr: ToastrService
    ){}

  ngOnInit(){
    const routeParams = this.route.snapshot.paramMap;
    var schoolClassId =  Number( routeParams.get("classId") );
    
    if( schoolClassId ){
      this.fetchStudents(schoolClassId)
      this.apiService.fetchSchoolClass(schoolClassId).subscribe(schoolClass=>{
        this.schoolClass = schoolClass
      })
    }else{
      this.searchStudents()
    }
    

  }

  getInvoiceDownloadLink(studentId:number){
    return this.apiService.apiEndPoint + '/students/' + studentId + '/invoice'
  }
  fetchStudents(schoolClassId:number){
    this.apiService.fetchStudentsInClass(schoolClassId).subscribe(students=>{
      this.students = students
    })
  }

  searchStudents(){
    this.apiService.searchStudents(this.searchLastName).subscribe(students=>{
      this.students = students
    })
  }

  addNewStudent(){
    this.student = new Student()
    this.student.schoolClassId = this.schoolClass.id
  }

  selectStudent(studentId:number){
    for(let x=0; x< this.students.length; x++){
      if(studentId == this.students[x].id){
        this.student = this.students[x];
      }
    }
  }

  saveStudent(){
    this.apiService.saveStudent(this.student).subscribe(student=>{
        this.fetchStudents(this.schoolClass.id)

        document.getElementById("close-school-form-modal")?.click();
        this.toastr.success('Student has been successfully saved','Invoicing System');
        this.addNewStudent()
    })
  }

  savePayment(){
    this.payment.id = 0
    this.payment.studentId = this.student.id
    this.apiService.savePayment (this.payment).subscribe(payment=>{
        this.payment = new Payment()

        document.getElementById("close-payment-form-modal")?.click();
        this.toastr.success('Payment has been successfully saved','Invoicing System');
    })
  }

}
