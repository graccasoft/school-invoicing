import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BillableItemsComponent } from './billable-items/billable-items.component';
import { LoginComponent } from './login/login.component';
import { PaymentsComponent } from './payments/payments.component';
import { SchoolClassesComponent } from './school-classes/school-classes.component';
import { StudentsComponent } from './students/students.component';

const routes: Routes = [
  {path: '',component:SchoolClassesComponent},
  {path: 'classes',component:SchoolClassesComponent},
  {path: 'students/:classId',component:StudentsComponent},
  {path: 'students',component:StudentsComponent},
  {path: 'payments',component:PaymentsComponent},
  {path: 'billable-items/:classId',component:BillableItemsComponent},
  {path: 'login',component:LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
