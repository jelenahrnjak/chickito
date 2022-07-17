import { NgModule } from '@angular/core'; 
import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';

import { LoginComponent } from './authentication/login/login.component';
import { SignUpComponent } from './authentication/sign-up/sign-up.component'; 
import { CoreComponent } from './core.component';

const routes: Routes = [

  {
    path: '', component: CoreComponent, children: [
      {
        path: 'login',
        component: LoginComponent, 
      },
      {
        path: 'signup',
        component: SignUpComponent, 
      },
      // { path: '**', component: Page404leavesComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule { }