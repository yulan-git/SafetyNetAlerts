import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, Subject } from 'rxjs';
import { Person } from 'src/app/core/models/person';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PersonsService {
  public persons$: Observable<Person[]>;
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
    this.persons$ = this.http.get<Person[]>(`${this.apiUrl}/person`).pipe(
      map(tabObj => {
      return tabObj.map(obj => {
        return new Person(obj)
      })
    }));
  }
}
