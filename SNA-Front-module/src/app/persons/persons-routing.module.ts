import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../auth/auth.guard';
import { PageAddPersonsComponent } from './pages/page-add-persons/page-add-persons.component';
import { PageEditPersonsComponent } from './pages/page-edit-persons/page-edit-persons.component';
import { PageListPersonsComponent } from './pages/page-list-persons/page-list-persons.component';

const routes: Routes = [
  { path: '', component: PageListPersonsComponent },
  { path: 'add-person', component: PageAddPersonsComponent },
  { path: 'edit-person', component: PageEditPersonsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonsRoutingModule { }
