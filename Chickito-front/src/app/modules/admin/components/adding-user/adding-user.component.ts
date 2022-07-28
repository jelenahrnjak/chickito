import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService, } from '../../services/user.service'; 
import { Subject } from 'rxjs/Subject'; 
import { ToastrService } from 'ngx-toastr';  

@Component({
  selector: 'app-adding-user',
  templateUrl: './adding-user.component.html',
  styleUrls: ['./adding-user.component.scss']
})
export class AddingUserComponent implements OnInit {

  form!: FormGroup;   

  constructor( 
    private toastr: ToastrService, 
    private userService: UserService,
    private router: Router, 
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(8), Validators.maxLength(32), Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[-+_!@#$%^&*.,?:;<>=`~\\]\x22\x27\(\)\{\}\|\/\[\\\\?]).{8,}$')])],
      repeatedPassword: ['', Validators.compose([Validators.required, Validators.minLength(8), Validators.maxLength(32), Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[-+_!@#$%^&*.,?:;<>=`~\\]\x22\x27\(\)\{\}\|\/\[\\\\?]).{8,}$')])],
      firstName: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      lastName: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      email: ['', Validators.compose([Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])],
      role : ['', Validators.compose([Validators.required])],
      sector : [''],
    });
  }

  onSubmit(){  
    
    if(this.form.get('password')?.value != this.form.get('repeatedPassword')?.value){
      this.toastr.error('Lozinke se ne poklapaju')
      return
    }

    this.userService.createUser(this.form.value)
      .subscribe(data => { 
        this.toastr.success('Novi zaposleni uspešno kreiran!')  
        this.router.navigate(['admin']);
      },
        error => { 
          console.log('Adding user error');  
          this.toastr.error(error['error'].message)
        });

  }

}
