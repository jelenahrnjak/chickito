import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, } from '../../services/auth.service';
import { UserService } from '../../services/user.service';
import { Subject } from 'rxjs/Subject';
import { takeUntil } from 'rxjs/operators';
// import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  constructor() { }
    // private toastr: ToastrService) { }

  ngOnInit(): void {
    // this.toastr.info('Are you the 6 fingered man?')
  }

}
