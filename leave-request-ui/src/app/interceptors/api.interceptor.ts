import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Constants} from "../constants/Constants";
import {Router} from "@angular/router";

@Injectable()
export class ApiInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const accessToken = localStorage.getItem(Constants.LOCAL_STORAGE_KEY_ACCESS_TOKEN);

    let authReq = request;
    if (accessToken) {
      authReq = request.clone({
        setHeaders: {
          'Authorization': `Bearer ${accessToken}`,
        }
      });
    }

    return next.handle(authReq).pipe(
      catchError((err, _) => {
        if (err && err.status && err.status === 401) {
          localStorage.clear();
          this.router.navigate(['/login']);
        }
        return throwError(err);
      })
    );
  }

}
