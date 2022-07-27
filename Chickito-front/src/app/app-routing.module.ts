import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 
import { AdminComponent } from './modules/admin/admin.component';

const routes: Routes = [
  {
    path: "",
    redirectTo: "auth/login",
    pathMatch: "full", 
  },
  // {
  //   path: 'admin',
  //   component: AdminComponent,
  //   loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule) 
 
  // },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
