import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {ApiService} from "../../services/api.service";
import {Router} from "@angular/router";
import {Constants} from "../../constants/Constants";
import {AlertUtil} from "../../utils/alert.util";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {

  signUpForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
  });

  constructor(
    private apiService: ApiService,
    private router: Router,
  ) { }

  onSignUpFormSubmit() {
    if (!this.validateForm()) return;

    this.apiService.signUp(this.signUpForm.value).subscribe({
      next: res => {
        console.log(res);
        localStorage.setItem(Constants.LOCAL_STORAGE_KEY_ACCESS_TOKEN, res.data.accessToken);
        this.router.navigate(['/main']);
      },
      error: err => {
        AlertUtil.showCommonErrorAlert(err);
      }
    });
  }

  validateForm(): boolean {
    if (!this.signUpForm.value.username?.trim()) {
      AlertUtil.showErrorAlert('Please enter the username');
      return false;
    }

    if (!this.signUpForm.value.password?.trim()) {
      AlertUtil.showErrorAlert('Please enter the password');
      return false;
    }

    if (this.signUpForm.value.password !== this.signUpForm.value.confirmPassword) {
      AlertUtil.showErrorAlert('Password and Confirm Password mismatch.');
      return false;
    }

    return true;
  }


}
