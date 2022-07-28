import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component'
import { HomeAdminComponent } from './components/home-admin/home-admin.component';
import { RoleguardService as RoleGuard } from '../core/guards/roleguard.service';
import { AddingUserComponent } from './components/adding-user/adding-user.component';
import { AddingCompanyComponent } from './components/adding-company/adding-company.component';

const routes: Routes = [
  {
    path: 'admin', 
    component: AdminComponent,
    children: [
      {
        path: '',
        component: HomeAdminComponent, 
      },
      {
        path: 'new-user',
        component: AddingUserComponent, 
      },
      {
        path: 'new-company',
        component: AddingCompanyComponent, 
      },
    ], 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
