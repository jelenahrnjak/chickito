import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';  
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component'; 

import { CoreModule } from './modules/core/core.module';  
import { HeaderComponent } from './modules/core/components/header/header.component'

@NgModule({
  declarations: [
    AppComponent,   
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    FormsModule,
    ReactiveFormsModule, 
  ],
  providers: [ 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
