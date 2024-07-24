import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../services/api.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AlertUtil} from "../../utils/alert.util";
import {Router} from "@angular/router";
import {Constants} from "../../constants/Constants";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(
    private apiService: ApiService,
    private router: Router,
  ) { }

  ngOnInit(): void {

  }

  onLoginFormSubmit(): void {
    if (!this.validateForm()) return;

    this.apiService.login(this.loginForm.value).subscribe({
      next: res => {
        localStorage.setItem(Constants.LOCAL_STORAGE_KEY_ACCESS_TOKEN, res.accessToken);
        this.router.navigate(['/main']);
      },
      error: err => {
        AlertUtil.showCommonErrorAlert(err);
      }
    });
  }

  validateForm(): boolean {
    this.loginForm.markAllAsTouched();
    return this.loginForm.valid;
  }

}
