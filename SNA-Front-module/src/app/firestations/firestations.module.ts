import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FirestationsRoutingModule } from './firestations-routing.module';
import { PageEditFirestationComponent } from './pages/page-edit-firestation/page-edit-firestation.component';
import { PageAddFirestationComponent } from './pages/page-add-firestation/page-add-firestation.component';
import { PageListFirestationComponent } from './pages/page-list-firestation/page-list-firestation.component';
import { PageDetailsFirestationComponent } from './pages/page-details-firestation/page-details-firestation.component';


@NgModule({
  declarations: [
    PageEditFirestationComponent,
    PageAddFirestationComponent,
    PageListFirestationComponent,
    PageDetailsFirestationComponent
  ],
  imports: [
    CommonModule,
    FirestationsRoutingModule
  ]
})
export class FirestationsModule { }
