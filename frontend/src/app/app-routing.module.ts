import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SchoolClassesComponent } from './school-classes/school-classes.component';
import { StudentsComponent } from './students/students.component';

const routes: Routes = [
  {path: '',component:SchoolClassesComponent},
  {path: 'classes',component:SchoolClassesComponent},
  {path: 'students/:classId',component:StudentsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
