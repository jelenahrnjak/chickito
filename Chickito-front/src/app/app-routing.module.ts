import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 

import { MyProfileComponent } from './components/shared/my-profile/my-profile.component';
import { ErrorPageComponent } from './components/core/error-page/error-page.component'; 

import { AddingBuildingComponent } from './components/admin/adding-building/adding-building.component';
import { AddingCompanyComponent } from './components/admin/adding-company/adding-company.component';
import { AddingUserComponent } from './components/admin/adding-user/adding-user.component';
import { HomeAdminComponent } from './components/admin/home-admin/home-admin.component';
import { UsersViewComponent } from './components/admin/users-view/users-view.component'; 
import { LoginComponent } from './components/auth/login/login.component'; 

import { HomeDirectorComponent } from './components/director/home-director/home-director.component';
import { OrdersViewDirectorComponent } from './components/director/orders-view-director/orders-view-director.component';
import { MachinesViewDirectorComponent } from './components/director/machines-view-director/machines-view-director.component';

import { HomeLeaderComponent } from './components/leader/home-leader/home-leader.component';
import { MachinesViewComponent } from './components/leader/machines-view/machines-view.component'; 
import { OrdersViewComponent } from './components/leader/orders-view/orders-view.component'; 
import { NewOrderComponent } from './components/leader/new-order/new-order.component'; 

import { HomeWorkerComponent } from './components/worker/home-worker/home-worker.component';

import { AuthentificationGuard } from './guards/authentification.guard'
import { RoleguardService as RoleGuard } from './guards/roleguard.service'; 

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent, 
  },
  
  {
    path: '',
    redirectTo : 'login', 
    pathMatch : "full"
  },  

  {
    path: 'my-profile',
    component: MyProfileComponent, 
    canActivate:[AuthentificationGuard]
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
      {
        path: 'orders', 
        component: OrdersViewDirectorComponent,
      },
      {
        path: 'machines', 
        component: MachinesViewDirectorComponent,
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
      {
        path: 'machines', 
        component: MachinesViewComponent,
      },
      {
        path: 'orders', 
        component: OrdersViewComponent,
      },
      {
        path: 'new-order', 
        component: NewOrderComponent,
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
  
 
  {
    path: '**',
    component : ErrorPageComponent
  }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
