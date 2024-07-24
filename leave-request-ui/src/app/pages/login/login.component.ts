import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../services/api.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {

  }

  onLoginFormSubmit(): void {
    if (!this.validateForm()) return;

    this.apiService.login({
      username: '',
      password: ''
    }).subscribe({
      next: res => {
        console.log(res);
      },
      error: err => {
        console.log(err);
      }
    });
  }

  validateForm(): boolean {
    return true;
  }

}
