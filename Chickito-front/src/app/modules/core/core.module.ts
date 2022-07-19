import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreComponent } from './core.component'; 
import {HttpClientModule} from '@angular/common/http'; ;
import { CoreRoutingModule } from './core-routing.module';

import { LoginComponent } from './authentication/login/login.component';
import { SignUpComponent } from './authentication/sign-up/sign-up.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptors/TokenInterceptor';
import { JwtHelperService, JWT_OPTIONS  } from '@auth0/angular-jwt';

import {ApiService} from './services/api.service'; 
import {AuthService} from './services/auth.service'; 
import {ConfigService} from './services/config.service';
import { ToastNoAnimationModule} from 'ngx-toastr';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ErrorPageComponent } from './components/error-page/error-page.component';

@NgModule({
  declarations: [
    CoreComponent,
    LoginComponent,
    SignUpComponent,
    ErrorPageComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    CoreRoutingModule,
    ToastNoAnimationModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [ 
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }, 
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    AuthService,
    ApiService, 
    ConfigService, 
  ],
})
export class CoreModule { }
