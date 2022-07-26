import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';  
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from '../../app-routing.module';
import { AppComponent } from './app.component'; 

import { CoreModule } from './modules/core/core.module'; 
import { AdminModule } from './modules/admin/admin.module' 
import { HeaderComponent } from './modules/core/components/header/header.component'
import { SideBarComponent } from './modules/core/components/side-bar/side-bar.component'

@NgModule({
  declarations: [
    AppComponent,   
    HeaderComponent,
    SideBarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    AdminModule,
    FormsModule,
    ReactiveFormsModule, 
  ],
  providers: [ 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
