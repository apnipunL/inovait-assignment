import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  FormGroupDirective, NgForm,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {ApiService} from "../../services/api.service";
import {Router} from "@angular/router";
import {Constants} from "../../constants/Constants";
import {AlertUtil} from "../../utils/alert.util";
import {ErrorStateMatcher} from "@angular/material/core";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {

  confirmPasswordValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
    return control.value.password === control.value.confirmPassword
      ? null
      : { PasswordNoMatch: true };
  };

  confirmPasswordErrorStateMatcher = new ConfirmPasswordErrorStateMatcher();

  signUpForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    confirmPassword: new FormControl('', [Validators.required]),
  }, { validators: this.confirmPasswordValidator });

  constructor(
    private apiService: ApiService,
    private router: Router,
  ) { }

  onSignUpFormSubmit() {
    if (!this.validateForm()) return;

    this.apiService.signUp(this.signUpForm.value).subscribe({
      next: async res => {
        await AlertUtil.showSuccessAlert('User account created successfully! You can login now.');
        this.router.navigate(['/login']);
      },
      error: err => {
        AlertUtil.showCommonErrorAlert(err);
      }
    });
  }

  validateForm(): boolean {
    this.signUpForm.markAllAsTouched();
    return this.signUpForm.valid;
  }

}

class ConfirmPasswordErrorStateMatcher implements ErrorStateMatcher{
  isErrorState(control: AbstractControl | null, form: FormGroupDirective | NgForm | null): boolean {
    if (!form?.form?.controls?.['confirmPassword']?.touched) return false;
    return form?.form?.controls?.['confirmPassword']?.hasError('required') || form?.form?.errors?.['PasswordNoMatch'];
  }

}
