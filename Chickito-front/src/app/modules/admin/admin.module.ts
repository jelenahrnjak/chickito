import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { AddingUserComponent } from './components/adding-user/adding-user.component';
import { HomeAdminComponent } from './components/home-admin/home-admin.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { TokenInterceptor } from '../../interceptors/TokenInterceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddingCompanyComponent } from './components/adding-company/adding-company.component';
import { AddingBuildingComponent } from './components/adding-building/adding-building.component';
import { UsersViewComponent } from './components/users-view/users-view.component';  
import {MatFormFieldModule} from '@angular/material/form-field'; 
import {MatIconModule} from '@angular/material/icon'; 

@NgModule({
  declarations: [
    AdminComponent,
    AddingUserComponent,
    HomeAdminComponent,
    AddingCompanyComponent,
    AddingBuildingComponent,
    UsersViewComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    MatFormFieldModule,
    MatIconModule
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
