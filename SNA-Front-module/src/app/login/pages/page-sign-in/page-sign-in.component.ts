import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-page-sign-in',
  templateUrl: './page-sign-in.component.html',
  styleUrls: ['./page-sign-in.component.scss']
})
export class PageSignInComponent implements OnInit {

  constructor(private router: Router,
    private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    console.log(form.value);
    this.authService.login(form.value)
      .subscribe(
        (resp: any) => {
          console.log("connection succeed", resp);
          this.router.navigate(['/persons/']);
          console.log(resp.roles)
          const admin = resp.roles;
        },
        error => {
          console.log('error while');
        }
      )
  }

}

