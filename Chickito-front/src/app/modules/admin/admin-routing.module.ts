import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component'
import { HomeAdminComponent } from './components/home-admin/home-admin.component';
import { RoleguardService as RoleGuard } from '../core/guards/roleguard.service';
import { AddingUserComponent } from './components/adding-user/adding-user.component';
import { AddingCompanyComponent } from './components/adding-company/adding-company.component';
import { AddingBuildingComponent } from './components/adding-building/adding-building.component';
import { UsersViewComponent } from './components/users-view/users-view.component';

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
      {
        path: 'new-building',
        component: AddingBuildingComponent, 
      },
      {
        path: 'users',
        component: UsersViewComponent, 
      },
    ], 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
