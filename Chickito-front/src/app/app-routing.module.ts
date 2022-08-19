import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 

import { AddingBuildingComponent } from './components/admin/adding-building/adding-building.component';
import { AddingCompanyComponent } from './components/admin/adding-company/adding-company.component';
import { AddingUserComponent } from './components/admin/adding-user/adding-user.component';
import { HomeAdminComponent } from './components/admin/home-admin/home-admin.component';
import { UsersViewComponent } from './components/admin/users-view/users-view.component'; 
import { LoginComponent } from './components/auth/login/login.component';
import { ErrorPageComponent } from './components/core/error-page/error-page.component'; 

import { AuthentificationGuard } from './guards/authentification.guard'
import { RoleguardService as RoleGuard } from './guards/roleguard.service';

//canActivate:[AuthentificationGuard] //OVO DODAVATI U PATH ZA SVE PUTANJE KOJIMA MOGU SVI REGISTROVANI DA PRISTUPE

const routes: Routes = [
  {
    path: "",
    redirectTo: "auth/login",
    pathMatch: "full", 
  },
  {
    path: 'auth/login',
    component: LoginComponent, 
  }, 
  // { 
  //   path: '**', component: ErrorPageComponent 
  // },
  {
    path: 'admin',
    component: HomeAdminComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ADMIN'  
    },
    children: [ 
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
    ]
  },
  // {
  //   path: 'admin',
  //   component: HomeAdminComponent, 
  // },
  // {
  //   path: 'admin/new-user',
  //   component: AddingUserComponent, 
  // },
  // {
  //   path: 'admin/new-company',
  //   component: AddingCompanyComponent, 
  // },
  // {
  //   path: 'admin/new-building',
  //   component: AddingBuildingComponent, 
  // },
  // {
  //   path: 'admin/users',
  //   component: UsersViewComponent, 
  // },
  
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
