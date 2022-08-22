import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../login/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthGuard implements CanActivate {

  constructor(private router: Router, private authService: AuthService) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const tokenRole = this.authService.getTokenRoles();
    console.log(tokenRole);
    let authorization: boolean;
    let admin = tokenRole.find((role:any) => role.authority == "ADMIN");
    if (admin) {
      authorization = true;
    } else {
      authorization = false;

      this.router.navigate(['/home/']);
    }
    return authorization;
  }
}