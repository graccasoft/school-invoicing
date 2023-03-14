import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';
import { Billable } from '../model/billable';
import { SchoolClass } from '../model/school-class';

@Component({
  selector: 'app-billable-items',
  templateUrl: './billable-items.component.html',
  styleUrls: ['./billable-items.component.css']
})
export class BillableItemsComponent {
  schoolClass!: SchoolClass
  billable: Billable = new Billable()
  billableItems!: Billable[]

  constructor(
    private apiService: ApiService,
    private toastr: ToastrService,
    private route: ActivatedRoute
  ){}

  ngOnInit(){
    const routeParams = this.route.snapshot.paramMap;
    var schoolClassId =  Number( routeParams.get("classId") );
    
    this.fetchItems(schoolClassId)
    this.apiService.fetchSchoolClass(schoolClassId).subscribe(schoolClass=>{
      this.schoolClass = schoolClass
    })
  }

  fetchItems(schoolClassId:number){
    this.apiService.fetchSchoolClassBillableItems(schoolClassId).subscribe(items=>{
      this.billableItems = items
    })
  }

  addNewItem(){
    this.billable = new Billable()
    this.billable.schoolClassId = this.schoolClass.id
  }

  selectItem(billableId:number){
    for(let x=0; x< this.billableItems.length; x++){
      if(billableId == this.billableItems[x].id){
        this.billable = this.billableItems[x];
      }
    }
    
  }

  saveItem(){
    this.apiService.saveBillable(this.billable).subscribe(billable=>{
      this.fetchItems(this.schoolClass.id)

      document.getElementById("close-billable-form-modal")?.click();
      this.toastr.success('Invoice item has been successfully saved','Invoicing System');
      this.addNewItem()
    })
  }
}
