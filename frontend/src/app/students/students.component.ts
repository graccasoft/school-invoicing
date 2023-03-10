import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { SchoolClass } from '../model/school-class';
import { Student } from '../model/student';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent {

  schoolClass!: SchoolClass
  students!: Student[]
  student: Student = new Student()

  constructor(
    private apiService: ApiService,
    private route: ActivatedRoute,
    private toastr: ToastrService
    ){}

  ngOnInit(){
    const routeParams = this.route.snapshot.paramMap;
    var schoolClassId =  Number( routeParams.get("classId") );
    
    this.fetchStudents(schoolClassId)
    this.apiService.fetchSchoolClass(schoolClassId).subscribe(schoolClass=>{
      this.schoolClass = schoolClass
    })

  }

  fetchStudents(schoolClassId:number){
    this.apiService.fetchStudentsInClass(schoolClassId).subscribe(students=>{
      this.students = students
    })
  }

  addNewStudent(){
    this.student = new Student()
    this.student.schoolClassId = this.schoolClass.id
  }

  selectStudent(studentId:number){
    this.student = this.students.filter((student)=>{
      return student.id = studentId
    })[0]
  }

  saveStudent(){
    this.apiService.saveStudent(this.student).subscribe(student=>{
        this.fetchStudents(this.schoolClass.id)

        document.getElementById("close-school-form-modal")?.click();
        this.toastr.success('Student has been successfully saved','Invoicing System');
        this.addNewStudent()
    })
  }

}
