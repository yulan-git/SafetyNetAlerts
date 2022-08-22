import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { map, Observable } from 'rxjs';
import { PersonI } from 'src/app/core/interfaces/person-i';
import { PersonDto } from 'src/app/core/models/person-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private jwtHelper = new JwtHelperService();

  constructor(private http: HttpClient,
    private router: Router
  ) { }

  signup(user: PersonDto): Observable<PersonDto> {
    return this.http.post<PersonDto>(`${environment.apiUrl}/auth/signup`, user)
  }

  login(user: PersonI): Observable<PersonI> {
    return this.http.post<PersonI>(`${environment.apiUrl}/auth/login`, user)
      .pipe(
        map(
          (resp: any) => {
            console.log(resp);
            localStorage.setItem('TOKEN_APPLI', resp.token);
            localStorage.setItem('ROLE', resp.roles)
            console.log('Token Save', resp.token);
            return resp;
          }
        )
      );
  }

  getUserId(): number {
    const token: any = this.getToken();
    const decode = this.jwtHelper.decodeToken(token);
    return decode.id;
  }

  getUserUsername(): string {
    const token: any = this.getToken();
    const decode = this.jwtHelper.decodeToken(token);
    return decode.sub;
  }

  getToken() {
    return localStorage.getItem('TOKEN_APPLI');
  }

  getJwtToken() {
    const token: any = this.getToken();
    const decode = this.jwtHelper.decodeToken(token);
    if (decode != null && decode.id != null && decode.sub != null) {
      if (!this.jwtHelper.isTokenExpired) {
        return { ...decode, token };
      } else {
        localStorage.removeItem('TOKEN_APPLI')
      }
    }
  }

  getTokenRoles() {
    const token: any = this.getToken();
    const decode = this.jwtHelper.decodeToken(token);
    const tokenRoles = decode.rol;
    return tokenRoles;
    // tokenRoles.forEach( role => {
    //   console.log(role.authority);
    //   return role.authority;
    // });
  }


  logout() {
    localStorage.removeItem('TOKEN_APPLI');
    localStorage.removeItem('USER_ID');
    this.router.navigate(['/login']);
  }

  isAuthenticated(): Boolean {
    const token: any = this.getToken();
    const decode = this.jwtHelper.decodeToken(token);
    return decode != null;
  }

  isAuthenticatedAsAdmin(): boolean {
    const roles = localStorage.getItem('ROLE');
    if (roles != null && roles.includes('ADMIN')) {
      return true;
    } else {
      console.log("Accès non autorisé ! ");
      return false;
    }
  }
}
