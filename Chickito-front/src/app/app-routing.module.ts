import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 

import { MyProfileComponent } from './components/shared/my-profile/my-profile.component';
import { ErrorPageComponent } from './components/core/error-page/error-page.component'; 
import { NewVacationRequestComponent } from './components/shared/new-vacation-request/new-vacation-request.component'
import { MyVacationRequestsComponent } from './components/shared/my-vacation-requests/my-vacation-requests.component'

import { AddingBuildingComponent } from './components/admin/adding-building/adding-building.component';
import { AddingCompanyComponent } from './components/admin/adding-company/adding-company.component';
import { AddingUserComponent } from './components/admin/adding-user/adding-user.component';
import { HomeAdminComponent } from './components/admin/home-admin/home-admin.component';
import { UsersViewComponent } from './components/admin/users-view/users-view.component'; 
import { LoginComponent } from './components/auth/login/login.component'; 

import { OrdersViewDirectorComponent } from './components/director/orders-view-director/orders-view-director.component';
import { MachinesViewDirectorComponent } from './components/director/machines-view-director/machines-view-director.component';
import { UsersDirectorComponent } from './components/director/users-director/users-director.component'; 
import { MahcineMaintenancesDirectorComponent } from './components/director/mahcine-maintenances-director/mahcine-maintenances-director.component'
import { VacationRequestsDirectorComponent } from './components/director/vacation-requests-director/vacation-requests-director.component'; 

import { MachinesViewComponent } from './components/leader/machines-view/machines-view.component'; 
import { OrdersViewComponent } from './components/leader/orders-view/orders-view.component'; 
import { NewOrderComponent } from './components/leader/new-order/new-order.component'; 
import { MachineMaintenancesViewComponent } from './components/leader/machine-maintenances-view/machine-maintenances-view.component'; 
import { NewMachineMaintenanceComponent } from './components/leader/new-machine-maintenance/new-machine-maintenance.component'; 
import { UsersLeaderComponent } from './components/leader/users-leader/users-leader.component'; 

import { MyMachinesComponent } from './components/worker/my-machines/my-machines.component';
import { MachineMaintenancesWorkerComponent } from './components/worker/machine-maintenances-worker/machine-maintenances-worker.component'

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
    path: 'new-vacation-request', 
    component: NewVacationRequestComponent,
    canActivate:[AuthentificationGuard]
  },

  {
    path: 'my-vacation-requests', 
    component: MyVacationRequestsComponent,
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
        component: MachinesViewDirectorComponent,
      },
      {
        path: 'orders', 
        component: OrdersViewDirectorComponent,
      },
      {
        path: 'machines', 
        component: MachinesViewDirectorComponent,
      },
      {
        path: 'users',
        component: UsersDirectorComponent
      }, 
      {
        path: 'machine-maintenances',
        component: MahcineMaintenancesDirectorComponent
      },
      {
        path: 'vacation-requests',
        component: VacationRequestsDirectorComponent
      }
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
        component: MachinesViewComponent,
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
      {
        path: 'machine-maintenances', 
        component: MachineMaintenancesViewComponent,
      },
      {
        path: 'new-machine-maintenance', 
        component: NewMachineMaintenanceComponent,
      },
      {
        path: 'workers',
        component: UsersLeaderComponent
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
        component: MyMachinesComponent,
      },
      {
        path: 'my-machines', 
        component: MyMachinesComponent,
      },
      {
        path: 'machine-maintenances',
        component: MachineMaintenancesWorkerComponent
      }
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
