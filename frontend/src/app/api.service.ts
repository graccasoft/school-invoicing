import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Billable } from './model/billable';
import { GenericResponse } from './model/generic-response';
import { Invoice } from './model/invoice';
import { Payment } from './model/payment';
import { SchoolClass } from './model/school-class';
import { Student } from './model/student';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  apiEndPoint:string = "http://localhost:8081"
  constructor(private http: HttpClient) { }

  fetchSchoolClasses():Observable<SchoolClass[]>{
    return this.http.get<SchoolClass[]>(this.apiEndPoint + '/school-class')
  }

  fetchStudentsInClass(schoolClassId: number):Observable<Student[]>{
    return this.http.get<Student[]>(this.apiEndPoint + '/school-class/' + schoolClassId + '/students')
  }

  saveSchoolClass(schoolClass: SchoolClass):Observable<SchoolClass>{
    return this.http.post<SchoolClass>(this.apiEndPoint + '/school-class', schoolClass)
  }

  saveBillable(billable:Billable): Observable<Billable>{
    return this.http.post<Billable>(this.apiEndPoint + '/billable', billable)
  }

  fetchStudent(studentId: number):Observable<Student>{
    return this.http.get<Student>(this.apiEndPoint + '/students/' + studentId )
  }

  fetchStudentInvoices(studentId: number):Observable<Invoice[]>{
    return this.http.get<Invoice[]>(this.apiEndPoint + '/students/' + studentId + '/invoices')
  }

  fetchStudentPayments(studentId: number):Observable<Payment[]>{
    return this.http.get<Payment[]>(this.apiEndPoint + '/students/' + studentId + '/payments')
  }

  fetchAllPayments(studentId: number):Observable<Payment[]>{
    return this.http.get<Payment[]>(this.apiEndPoint + '/payments/')
  }

  savePayment(payment:Payment): Observable<Payment>{
    return this.http.post<Payment>(this.apiEndPoint + '/payments', payment)
  }

  generateSchoolClassInvoices(schoolClassId:number): Observable<GenericResponse>{
    return this.http.post<GenericResponse>(this.apiEndPoint + '/invoice/school', {'schoolClassId':schoolClassId})
  }

  generateStudentInvoices(studentId:number): Observable<GenericResponse>{
    return this.http.post<GenericResponse>(this.apiEndPoint + '/invoice/student', {'studentId':studentId})
  }


}
