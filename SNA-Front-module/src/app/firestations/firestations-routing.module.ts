import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageAddFirestationComponent } from './pages/page-add-firestation/page-add-firestation.component';
import { PageDetailsFirestationComponent } from './pages/page-details-firestation/page-details-firestation.component';
import { PageEditFirestationComponent } from './pages/page-edit-firestation/page-edit-firestation.component';
import { PageListFirestationComponent } from './pages/page-list-firestation/page-list-firestation.component';

const routes: Routes = [
  {path: '', component: PageListFirestationComponent},
  {path: 'add-firestation', component: PageAddFirestationComponent},
  { path: 'edit-firestation', component: PageEditFirestationComponent },
  { path: ':station', component: PageDetailsFirestationComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FirestationsRoutingModule { }
