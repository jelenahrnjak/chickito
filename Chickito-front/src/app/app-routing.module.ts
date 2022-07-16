import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './modules/core/authentication/login/login.component';
import { SignUpComponent } from './modules/core/authentication/sign-up/sign-up.component';
import { CoreComponent } from './modules/core/core.component';

const routes: Routes = [
  {
    path: 'auth',
    component: CoreComponent,
    loadChildren: () => import('./modules/core/core.module').then(m => m.CoreModule)
    // children : [
    //   {
    //     path: 'login',
    //     component: LoginComponent, 
    //   },
    //   {
    //     path: 'sign-up',
    //     component: SignUpComponent, 
    //   },
    // ]
 
  },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
