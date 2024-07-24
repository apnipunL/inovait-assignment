import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) { }

  login(data: any): Observable<any> {
    return this.httpClient.post('/auth/login', data);
  }

  signUp(data: any): Observable<any> {
    return this.httpClient.post('/auth/register', data);
  }

  createLeaveRequest(data: any): Observable<any> {
    return this.httpClient.post('/leave-requests', data);
  }

  updateLeaveRequest(data: any): Observable<any> {
    return this.httpClient.put('/leave-requests', data);
  }

  deleteLeaveRequest(id: number): Observable<any> {
    return this.httpClient.delete('/leave-requests/' + id);
  }

  getAllLeaveRequest(): Observable<any> {
    return this.httpClient.get('/leave-requests');
  }

}
