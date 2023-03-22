import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from './../environments/environment';
import { Billable } from './model/billable';
import { GenericResponse } from './model/generic-response';
import { Invoice } from './model/invoice';
import { Payment } from './model/payment';
import { SchoolClass } from './model/school-class';
import { Statement } from './model/statement';
import { Student } from './model/student';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  apiEndPoint:string = environment.apiEndPoint
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'my-auth-token'
    })
  };

  constructor(private http: HttpClient) {
    this.httpOptions.headers = this.httpOptions.headers.set('Authorization', 'Bearer ' + localStorage.getItem('jwt_token'));
   }

  fetchSchoolClasses():Observable<SchoolClass[]>{
    return this.http.get<SchoolClass[]>(this.apiEndPoint + '/school-class',this.httpOptions)
  }

  fetchSchoolClass(schoolClassId:number):Observable<SchoolClass>{
    return this.http.get<SchoolClass>(this.apiEndPoint + '/school-class/' + schoolClassId,this.httpOptions)
  }

  fetchStudentsInClass(schoolClassId: number):Observable<Student[]>{
    return this.http.get<Student[]>(this.apiEndPoint + '/school-class/' + schoolClassId + '/students',this.httpOptions)
  }

  fetchSchoolClassBillableItems(schoolClassId:number):Observable<Billable[]>{
    return this.http.get<Billable[]>(this.apiEndPoint + '/school-class/' + schoolClassId + '/billable-items',this.httpOptions)
  }

  fetchStudentStatement(studentId:number): Observable<Statement>{
    return this.http.get<Statement>(this.apiEndPoint + '/students/' + studentId + '/statement', this.httpOptions )
  }

  saveSchoolClass(schoolClass: SchoolClass):Observable<SchoolClass>{
    return this.http.post<SchoolClass>(this.apiEndPoint + '/school-class', schoolClass,this.httpOptions)
  }

  saveBillable(billable:Billable): Observable<Billable>{
    return this.http.post<Billable>(this.apiEndPoint + '/billable', billable,this.httpOptions)
  }

  fetchStudent(studentId: number):Observable<Student>{
    return this.http.get<Student>(this.apiEndPoint + '/students/' + studentId,this.httpOptions )
  }

  searchStudents(lastName: string):Observable<Student[]>{
    return this.http.get<Student[]>(this.apiEndPoint + '/students?lastName=' + lastName,this.httpOptions )
  }

  saveStudent(student:Student):Observable<Student>{
    return this.http.post<Student>(this.apiEndPoint + "/students", student,this.httpOptions)
  }

  fetchStudentInvoices(studentId: number):Observable<Invoice[]>{
    return this.http.get<Invoice[]>(this.apiEndPoint + '/students/' + studentId + '/invoices',this.httpOptions)
  }

  fetchStudentPayments(studentId: number):Observable<Payment[]>{
    return this.http.get<Payment[]>(this.apiEndPoint + '/students/' + studentId + '/payments',this.httpOptions)
  }

  fetchAllPayments():Observable<Payment[]>{
    return this.http.get<Payment[]>(this.apiEndPoint + '/payments',this.httpOptions)
  }

  savePayment(payment:Payment): Observable<Payment>{
    return this.http.post<Payment>(this.apiEndPoint + '/payments', payment,this.httpOptions)
  }

  generateSchoolClassInvoices(schoolClassId:number,invoicesTitle:string): Observable<GenericResponse>{
    return this.http.post<GenericResponse>(this.apiEndPoint + '/invoice/school', 
      {'schoolClassId':schoolClassId,'title':invoicesTitle},this.httpOptions)
  }

  generateStudentInvoices(studentId:number,invoicesTitle:string): Observable<GenericResponse>{
    return this.http.post<GenericResponse>(this.apiEndPoint + '/invoice/student', 
      {'studentId':studentId,'title':invoicesTitle},this.httpOptions)
  }


}
