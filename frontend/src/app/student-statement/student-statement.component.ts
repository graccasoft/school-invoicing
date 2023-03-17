import { formatCurrency, formatDate } from '@angular/common';
import { Component, Input, SimpleChanges } from '@angular/core';
import { ApiService } from '../api.service';
import { Statement } from '../model/statement';
import { Student } from '../model/student';

@Component({
  selector: 'app-student-statement',
  templateUrl: './student-statement.component.html',
  styleUrls: ['./student-statement.component.css']
})
export class StudentStatementComponent {

  @Input() studentId: number=0
  statement!: Statement
  student!: Student

  constructor(private apiService: ApiService){}

  fetchStatementInfo(){
    this.apiService.fetchStudentStatement(this.studentId).subscribe(statement=>{
      statement.items.forEach((item, index)=>{
        statement.items[index].date = formatDate(item.date,"dd MMM yyyy","en_ZW")
        statement.items[index].formattedAmount = formatCurrency(item.amount,"en_ZW","$")
      })
      this.statement = statement
    })
    this.apiService.fetchStudent(this.studentId).subscribe(student=>{
      this.student = student
    })
  }
  ngOnInit(){
    this.fetchStatementInfo()
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes)
    this.fetchStatementInfo()
  }
}
