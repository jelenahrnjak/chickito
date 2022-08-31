import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';  
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field'; 
import { MatIconModule } from '@angular/material/icon'; 
import { NgbModule } from '@ng-bootstrap/ng-bootstrap'; 
import { Ng2SearchPipeModule } from 'ng2-search-filter';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptors/TokenInterceptor';
import { JwtHelperService, JWT_OPTIONS  } from '@auth0/angular-jwt';
import { ToastNoAnimationModule} from 'ngx-toastr';
import {HttpClientModule} from '@angular/common/http'; 

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component'; 
 
import { HeaderComponent } from './components/core/header/header.component'
import { SideBarComponent } from './components/core/side-bar/side-bar.component'
import { ErrorPageComponent } from './components/core/error-page/error-page.component'; 
import { LoginComponent } from './components/auth/login/login.component';

import { AddingUserComponent } from './components/admin/adding-user/adding-user.component';
import { HomeAdminComponent } from './components/admin/home-admin/home-admin.component';
import { AddingCompanyComponent } from './components/admin/adding-company/adding-company.component';
import { AddingBuildingComponent } from './components/admin/adding-building/adding-building.component';
import { UsersViewComponent } from './components/admin/users-view/users-view.component';  

import { ApiService } from './services/core/api.service'; 
import { AuthService } from './services/core/auth.service'; 
import { ConfigService } from './services/core/config.service';
import { AuthguardService } from './guards/authguard.service';
import { NewOrderComponent } from './components/leader/new-order/new-order.component';
import { OrdersViewComponent } from './components/leader/orders-view/orders-view.component';
import { MachinesViewComponent } from './components/leader/machines-view/machines-view.component';
import { MyProfileComponent } from './components/shared/my-profile/my-profile.component';
import { OrdersViewDirectorComponent } from './components/director/orders-view-director/orders-view-director.component';
import { AgmCoreModule } from '@agm/core'; 
import { MachinesViewDirectorComponent } from './components/director/machines-view-director/machines-view-director.component';
import { MachineMaintenancesViewComponent } from './components/leader/machine-maintenances-view/machine-maintenances-view.component';
import { NewMachineMaintenanceComponent } from './components/leader/new-machine-maintenance/new-machine-maintenance.component';
import { UsersLeaderComponent } from './components/leader/users-leader/users-leader.component';
import { UsersDirectorComponent } from './components/director/users-director/users-director.component';
import { MahcineMaintenancesDirectorComponent } from './components/director/mahcine-maintenances-director/mahcine-maintenances-director.component';
import { MyMachinesComponent } from './components/worker/my-machines/my-machines.component';
import { NewVacationRequestComponent } from './components/shared/new-vacation-request/new-vacation-request.component';
import { MachineMaintenancesWorkerComponent } from './components/worker/machine-maintenances-worker/machine-maintenances-worker.component';
import { MyVacationRequestsComponent } from './components/shared/my-vacation-requests/my-vacation-requests.component';
import { VacationRequestsDirectorComponent } from './components/director/vacation-requests-director/vacation-requests-director.component';


@NgModule({
  declarations: [
    AppComponent,   
    HeaderComponent,
    SideBarComponent,
    AddingUserComponent,
    HomeAdminComponent,
    AddingCompanyComponent,
    AddingBuildingComponent,
    UsersViewComponent,
    LoginComponent,
    ErrorPageComponent, 
    NewOrderComponent,
    OrdersViewComponent,
    MachinesViewComponent,
    MyProfileComponent,
    OrdersViewDirectorComponent,
    MachinesViewDirectorComponent,
    MachineMaintenancesViewComponent,
    NewMachineMaintenanceComponent,
    UsersLeaderComponent,
    UsersDirectorComponent,
    MahcineMaintenancesDirectorComponent,
    MyMachinesComponent,
    NewVacationRequestComponent,
    MachineMaintenancesWorkerComponent,
    MyVacationRequestsComponent,
    VacationRequestsDirectorComponent, 
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ToastNoAnimationModule.forRoot(),
    BrowserModule,
    AppRoutingModule, 
    FormsModule,
    ReactiveFormsModule, 
    MatFormFieldModule,
    MatIconModule,
    NgbModule,
    Ng2SearchPipeModule,
    AgmCoreModule.forRoot({
      apiKey : 'AIzaSyCX5DQFPxHlQlEeFkkWzTJ41PU6FehGzVs'
    })
  ],
  providers: [     
    {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
    },
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    AuthService,
    ApiService, 
    ConfigService, 
    AuthguardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
