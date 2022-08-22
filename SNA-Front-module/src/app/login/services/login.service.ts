import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Address } from 'src/app/core/models/address';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public addresses$: Observable<Address[]>;
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
    this.addresses$ = this.http.get<Address[]>(`${this.apiUrl}/address`).pipe(
      map(tabObj => {
        return tabObj.map(obj => {
          return new Address(obj)
        })
      }));
  }

  public getById(addressId: number): Observable<Address> {
    return this.http.get<Address>(`${this.apiUrl}/address/${addressId}`);
  }
  
}
