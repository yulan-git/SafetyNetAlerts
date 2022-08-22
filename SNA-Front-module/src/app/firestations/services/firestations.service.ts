import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Firestation } from 'src/app/core/models/firestation';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FirestationsService {
  public firestations$: Observable<Firestation[]>;
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
    this.firestations$ = this.http.get<Firestation[]>(`${this.apiUrl}/firestation`);
  }

  public getById(station: number): Observable<Firestation> {
    return this.http.get<Firestation>(`${this.apiUrl}/firestation/${station}`);
  }

  public getChildren(addressId: number): Observable<string> {
    return this.http.get<string>(`${this.apiUrl}/childAlert?address=${addressId}`);
  }
}
