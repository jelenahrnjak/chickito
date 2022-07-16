import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreComponent } from './core.component'; 
import {HttpClientModule} from '@angular/common/http'; ;
import { CoreRoutingModule } from './core-routing.module';

import { LoginComponent } from './authentication/login/login.component';
import { SignUpComponent } from './authentication/sign-up/sign-up.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptors/TokenInterceptor';

import {ApiService} from './services/api.service'; 
import {AuthService} from './services/auth.service';
import {UserService} from './services/user.service';
import {ConfigService} from './services/config.service';
import { ToastrModule} from 'ngx-toastr';


@NgModule({
  declarations: [
    CoreComponent,
    LoginComponent,
    SignUpComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    CoreRoutingModule,
    ToastrModule.forRoot(),
  ],
  providers: [ 
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }, 
    AuthService,
    ApiService,
    UserService,
    ConfigService, 
  ],
})
export class CoreModule { }
