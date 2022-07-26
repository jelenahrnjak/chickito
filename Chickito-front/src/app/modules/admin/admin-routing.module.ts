import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component'
import { HomeAdminComponent } from './components/home-admin/home-admin.component';
import { RoleguardService as RoleGuard } from '../core/guards/roleguard.service';

const routes: Routes = [
  {
    path: 'admin', 
    component: HomeAdminComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
