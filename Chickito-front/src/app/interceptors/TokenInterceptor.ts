import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpInterceptor,
  HttpEvent
} from '@angular/common/http'; 

import { Observable, } from 'rxjs/Observable';

import { _throw } from 'rxjs/observable/throw';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor() { }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (localStorage.getItem("jwt") != null) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${sessionStorage.getItem("jwt")}` 
        }
      });
    }
    return next.handle(request);
  }
}