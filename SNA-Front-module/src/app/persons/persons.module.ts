import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PersonsRoutingModule } from './persons-routing.module';
import { PageListPersonsComponent } from './pages/page-list-persons/page-list-persons.component';
import { PageAddPersonsComponent } from './pages/page-add-persons/page-add-persons.component';
import { PageEditPersonsComponent } from './pages/page-edit-persons/page-edit-persons.component';
import { CardDetailsPersonComponent } from './pages/card-details-person/card-details-person.component';
import { AuthService } from '../login/services/auth.service';


@NgModule({
  declarations: [
    PageListPersonsComponent,
    PageAddPersonsComponent,
    PageEditPersonsComponent,
    CardDetailsPersonComponent
  ],
  imports: [
    CommonModule,
    PersonsRoutingModule
  ]
})
export class PersonsModule { }
