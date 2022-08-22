import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 

import { AddingBuildingComponent } from './components/admin/adding-building/adding-building.component';
import { AddingCompanyComponent } from './components/admin/adding-company/adding-company.component';
import { AddingUserComponent } from './components/admin/adding-user/adding-user.component';
import { HomeAdminComponent } from './components/admin/home-admin/home-admin.component';
import { UsersViewComponent } from './components/admin/users-view/users-view.component'; 
import { LoginComponent } from './components/auth/login/login.component'; 

import { HomeDirectorComponent } from './components/director/home-director/home-director.component';

import { HomeLeaderComponent } from './components/leader/home-leader/home-leader.component';

import { HomeWorkerComponent } from './components/worker/home-worker/home-worker.component';

import { AuthentificationGuard } from './guards/authentification.guard'
import { RoleguardService as RoleGuard } from './guards/roleguard.service';

//canActivate:[AuthentificationGuard] //OVO DODAVATI U PATH ZA SVE PUTANJE KOJIMA MOGU SVI REGISTROVANI DA PRISTUPE

const routes: Routes = [
  {
    path: "",
    redirectTo: "login",
    pathMatch: "full", 
  },
  {
    path: 'login',
    component: LoginComponent, 
  }, 

  {
    path: 'admin',
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ADMIN'  
    },
    children: [
      {
        path: '', 
        component: HomeAdminComponent,
      },
      {
        path: 'new-user',
        component: AddingUserComponent
      }, 
      {
        path: 'new-company',
        component: AddingCompanyComponent
      }, 
      {
        path: 'new-building',
        component: AddingBuildingComponent
      }, 
      {
        path: 'users',
        component: UsersViewComponent
      }, 
      ]
  },

  {
    path: 'director',
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'DIRECTOR'  
    },
    children: [
      {
        path: '', 
        component: HomeDirectorComponent,
      },
      ]
  },

  {
    path: 'leader',
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'LEADER'  
    },
    children: [
      {
        path: '', 
        component: HomeLeaderComponent,
      },
      ]
  },

  {
    path: 'worker',
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'WORKER'  
    },
    children: [
      {
        path: '', 
        component: HomeWorkerComponent,
      },
      ]
  },
  
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
