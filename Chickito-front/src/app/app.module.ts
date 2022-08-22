import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';  
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field'; 
import { MatIconModule } from '@angular/material/icon'; 
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptors/TokenInterceptor';
import { JwtHelperService, JWT_OPTIONS  } from '@auth0/angular-jwt';
import { ToastNoAnimationModule} from 'ngx-toastr';
import {HttpClientModule} from '@angular/common/http'; 

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component'; 
 
import { HeaderComponent } from './components/core/header/header.component'
import { SideBarComponent } from './components/core/side-bar/side-bar.component'
import { ErrorPageComponent } from './components/core/error-page/error-page.component'; 
import { LoginComponent } from './components/auth/login/login.component';

import { AddingUserComponent } from './components/admin/adding-user/adding-user.component';
import { HomeAdminComponent } from './components/admin/home-admin/home-admin.component';
import { AddingCompanyComponent } from './components/admin/adding-company/adding-company.component';
import { AddingBuildingComponent } from './components/admin/adding-building/adding-building.component';
import { UsersViewComponent } from './components/admin/users-view/users-view.component';  

import { ApiService } from './services/core/api.service'; 
import { AuthService } from './services/core/auth.service'; 
import { ConfigService } from './services/core/config.service';
import { AuthguardService } from './guards/authguard.service';
import { HomeLeaderComponent } from './components/leader/home-leader/home-leader.component';
import { HomeDirectorComponent } from './components/director/home-director/home-director.component';
import { HomeWorkerComponent } from './components/worker/home-worker/home-worker.component';

@NgModule({
  declarations: [
    AppComponent,   
    HeaderComponent,
    SideBarComponent,
    AddingUserComponent,
    HomeAdminComponent,
    AddingCompanyComponent,
    AddingBuildingComponent,
    UsersViewComponent,
    LoginComponent,
    ErrorPageComponent,
    HomeLeaderComponent,
    HomeDirectorComponent,
    HomeWorkerComponent, 
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ToastNoAnimationModule.forRoot(),
    BrowserModule,
    AppRoutingModule, 
    FormsModule,
    ReactiveFormsModule, 
    MatFormFieldModule,
    MatIconModule,
    NgbModule,
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
    AuthguardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
