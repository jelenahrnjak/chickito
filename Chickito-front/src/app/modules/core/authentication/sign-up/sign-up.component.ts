import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, } from '../../services/auth.service'; 
import { Subject } from 'rxjs/Subject'; 
import { ToastrService } from 'ngx-toastr';  

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  form!: FormGroup;
  submitted = false; 
  private ngUnsubscribe: Subject<void> = new Subject<void>(); 

  constructor( 
    private toastr: ToastrService, 
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {  

    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(8), Validators.maxLength(32), Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[-+_!@#$%^&*.,?:;<>=`~\\]\x22\x27\(\)\{\}\|\/\[\\\\?]).{8,}$')])],
      repeatedPassword: ['', Validators.compose([Validators.required, Validators.minLength(8), Validators.maxLength(32), Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[-+_!@#$%^&*.,?:;<>=`~\\]\x22\x27\(\)\{\}\|\/\[\\\\?]).{8,}$')])],
      firstName: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      lastName: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      email: ['', Validators.compose([Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])],
      role : [false, Validators.compose([Validators.required])],
      sector : ['', Validators.compose([Validators.required])],
    });
  } 

  onSubmit(){  
    alert(this.form.get('role')?.value)
    if(this.form.get('password')?.value != this.form.get('repeatedPassword')?.value){
      this.toastr.error('Lozinke se ne poklapaju')
      return
    }

    this.authService.signup(this.form.value)
      .subscribe(data => {
        console.log(data); 
        this.toastr.success('Registracija uspešna! Kada rukovodilac potvrdi vašu registraciju dobićete email potvrdu na: ' + this.form.get('email')?.value )  
        this.router.navigate(['']);
      },
        error => { 
          console.log('Sign up error');  
          this.toastr.error(error['error'].message)
        });

  }
 
}
