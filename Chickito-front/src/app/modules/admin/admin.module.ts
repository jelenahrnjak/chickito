import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { AddingUserComponent } from './components/adding-user/adding-user.component';
import { HomeAdminComponent } from './components/home-admin/home-admin.component';


@NgModule({
  declarations: [
    AdminComponent,
    AddingUserComponent,
    HomeAdminComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
