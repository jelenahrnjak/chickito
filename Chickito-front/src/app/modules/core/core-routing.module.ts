import { NgModule } from '@angular/core'; 
import {RouterModule, Routes} from '@angular/router'; 

import { LoginComponent } from './authentication/login/login.component';
import { SignUpComponent } from './authentication/sign-up/sign-up.component'; 
import { CoreComponent } from './core.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';

const routes: Routes = [

  {
    path: 'auth', component: CoreComponent, children: [
      {
        path: 'login',
        component: LoginComponent, 
      },
      {
        path: 'signup',
        component: SignUpComponent, 
      },
      { path: '**', component: ErrorPageComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule { }