import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Person } from 'src/app/core/models/person';
import { PersonsService } from '../../services/persons.service';

@Component({
  selector: 'app-page-list-persons',
  templateUrl: './page-list-persons.component.html',
  styleUrls: ['./page-list-persons.component.scss']
})
export class PageListPersonsComponent implements OnInit {
  personsList$: Observable<Person[]>;
  constructor(private personsService: PersonsService) {
    // this.personsService.persons$.subscribe((data) => {
    //   this.personsList = [...data];
    //   console.log(this.personsList);
    // })
   this.personsList$ = this.personsService.persons$;
  }

  ngOnInit(): void {
  }

}
