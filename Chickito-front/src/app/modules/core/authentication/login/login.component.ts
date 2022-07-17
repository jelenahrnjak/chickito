import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../services/auth.service'; 
import {Subject} from 'rxjs/Subject';
import { ToastrService } from 'ngx-toastr';  
import {takeUntil} from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form!: FormGroup; 
  private ngUnsubscribe: Subject<void> = new Subject<void>();


  constructor(
    private toastr: ToastrService, 
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() { 
    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])]
    });
  }

  onSubmit() { 

    this.authService.login(this.form.value)
      .subscribe(data => { 
          this.router.navigate(['']);
        },
        error => { 
          this.toastr.error('Pogrešno korisničko ime ili lozinka')
        });
  }

}
