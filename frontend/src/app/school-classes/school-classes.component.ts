import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';
import { SchoolClass } from '../model/school-class';

@Component({
  selector: 'app-school-classes',
  templateUrl: './school-classes.component.html',
  styleUrls: ['./school-classes.component.css']
})
export class SchoolClassesComponent {

  schoolClasses!: SchoolClass[] 
  schoolClass: SchoolClass = new SchoolClass(0,"")
  formIsValid = true;
  invoiceTitle = ""

  constructor(
    private apiService: ApiService,
    private toastr: ToastrService
    ){}

  ngOnInit(){
    this.fetchSchoolClasses()
  }

  fetchSchoolClasses(){
    this.apiService.fetchSchoolClasses().subscribe(classes=>{
      this.schoolClasses = classes
    })
  }

  toggleFormValidation(valid:boolean){
    this.formIsValid = valid
  }


  selectSchoolClass(schoolClassId:number){
    for(let x=0; x< this.schoolClasses.length; x++){
      if(schoolClassId == this.schoolClasses[x].id){
        this.schoolClass = this.schoolClasses[x];
      }
    }
    
  }

  saveSchoolClass(){
    if( this.schoolClass.description.length > 4 ){
      this.apiService.saveSchoolClass(this.schoolClass).subscribe(thischoolClass=>{
        this.fetchSchoolClasses()

        document.getElementById("close-school-form-modal")?.click();
        this.toastr.success('Class has been successfully saved','Invoicing System');
        this.schoolClass = new SchoolClass(0,"")
      })
    }
  }

  runInvoices(schoolClassId:number){
    this.selectSchoolClass(schoolClassId)

    if( confirm('Are you sure you want to generate invoices for '+ this.schoolClass.description)){
      document.getElementById("close-invoices-form-modal")?.click();
      this.apiService.generateSchoolClassInvoices(this.schoolClass.id, this.invoiceTitle ).subscribe(response=>{
        this.toastr.success( response.message ,'Invoicing System');
      })
    }
  }
}
