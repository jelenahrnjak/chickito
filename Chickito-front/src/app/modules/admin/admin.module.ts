import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { AddingUserComponent } from './components/adding-user/adding-user.component';
import { HomeAdminComponent } from './components/home-admin/home-admin.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { TokenInterceptor } from '../../interceptors/TokenInterceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http'; 

@NgModule({
  declarations: [
    AdminComponent,
    AddingUserComponent,
    HomeAdminComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [  
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }, 
  ],
})
export class AdminModule { }
