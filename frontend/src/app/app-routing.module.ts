import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SchoolClassesComponent } from './school-classes/school-classes.component';

const routes: Routes = [
  {path: '',component:SchoolClassesComponent},
  {path: 'classes',component:SchoolClassesComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
