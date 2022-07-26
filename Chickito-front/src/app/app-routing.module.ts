import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './modules/admin/admin.component';
import { LoginComponent } from './modules/core/authentication/login/login.component';
import { SignUpComponent } from './modules/core/authentication/sign-up/sign-up.component';
import { CoreComponent } from './modules/core/core.component';

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
