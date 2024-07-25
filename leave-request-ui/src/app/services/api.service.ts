import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Constants} from "../constants/Constants";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) { }

  login(data: any): Observable<any> {
    return this.httpClient.post(Constants.API_BASE_URL + '/auth/login', data);
  }

  signUp(data: any): Observable<any> {
    return this.httpClient.post(Constants.API_BASE_URL + '/auth/register', data);
  }

  getCurrentUser(): Observable<any> {
    return this.httpClient.get(Constants.API_BASE_URL + '/auth/me');
  }

  createLeaveRequest(data: any): Observable<any> {
    return this.httpClient.post(Constants.API_BASE_URL + '/leave-requests', data);
  }

  updateLeaveRequest(data: any): Observable<any> {
    return this.httpClient.put(Constants.API_BASE_URL + '/leave-requests', data);
  }

  deleteLeaveRequest(id: number): Observable<any> {
    return this.httpClient.delete(Constants.API_BASE_URL + '/leave-requests/' + id);
  }

  getAllLeaveRequest(): Observable<any> {
    return this.httpClient.get(Constants.API_BASE_URL + '/leave-requests');
  }

  getLeaveRequestById(id: number): Observable<any> {
    return this.httpClient.get(Constants.API_BASE_URL + '/leave-requests/' + id);
  }

}
