import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PersonIdI } from 'src/app/core/interfaces/person-id-i';
import { Address } from 'src/app/core/models/address';
import { Person } from 'src/app/core/models/person';
import { AuthService } from '../../services/auth.service';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-page-sign-up',
  templateUrl: './page-sign-up.component.html',
  styleUrls: ['./page-sign-up.component.scss']
})
export class PageSignUpComponent implements OnInit {

  public addresses$!: Observable<Address[]>;
  public address!: Address;
  public personId!: PersonIdI;
  public newAddress!: Address;
  public signupForm!: FormGroup;
  public submitted = false;
  public person!: Person;
  pwdPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"

  constructor(private router: Router,
    private authService: AuthService, private loginService: LoginService) {
    this.addresses$ = this.loginService.addresses$;
  }
  
  ngOnInit(): void {
    this.initForm();
    
  }

  initForm() {
    this.signupForm = new FormGroup({
      lastName: new FormControl('', Validators.required),
      firstName: new FormControl('', [Validators.required]),
      phone: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.pattern(this.pwdPattern)]),
      address: new FormControl('', [Validators.required, Validators.minLength(8), Validators.pattern(this.pwdPattern)])
    })
  }

  onSubmit() {
    console.log(this.signupForm.value.address);
    
    try {
      this.loginService.getById(this.signupForm.value.address).subscribe((address: any) => {
        this.address = { ...address };
        const person = {
          lastName: this.signupForm.value.lastName,
          firstName : this.signupForm.value.firstName,
          phone : this.signupForm.value.phone,
          email : this.signupForm.value.email,
          password : this.signupForm.value.password,
          address : this.address
          }
  
  
          this.authService.signup(person).subscribe((response) => {
            this.router.navigate(["/sign-in"]);
            console.log(response, "Création réussie")
            this.submitted = true;
          });
      });
      } catch {
        console.log("__Error handled gracefully.")
      }
    

    
  }

}
