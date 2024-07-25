import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Constants} from "../../../constants/Constants";
import {AlertUtil} from "../../../utils/alert.util";
import {ApiService} from "../../../services/api.service";

@Component({
  selector: 'app-leave-request-form',
  templateUrl: './leave-request-form.component.html',
  styleUrls: ['./leave-request-form.component.scss']
})
export class LeaveRequestFormComponent implements OnInit {

  mode: 'ADD' | 'EDIT';
  leaveRequestId: number;
  leaveRequestForm = new FormGroup({
    leaveType: new FormControl(null, [Validators.required]),
    startDate: new FormControl(null, [Validators.required]),
    endDate: new FormControl(null, [Validators.required]),
    reason: new FormControl(''),
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) private dialogData: {mode: 'ADD' | 'EDIT', leaveRequestId: number},
    private dialogRef: MatDialogRef<LeaveRequestFormComponent>,
    private apiService: ApiService,
  ) {
    this.mode = dialogData.mode;
    this.leaveRequestId = dialogData.leaveRequestId;
  }

  ngOnInit(): void {
    if (this.mode === 'EDIT') {
      this.apiService.getLeaveRequestById(this.leaveRequestId).subscribe({
        next: res => {
          this.leaveRequestForm.patchValue({
            leaveType: res.leaveType,
            startDate: res.startDate,
            endDate: res.endDate,
            reason: res.reason,
          });
        },
        error: err => {
          AlertUtil.showCommonErrorAlert(err);
        }
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  onLeaveRequestFormSubmit(): void {
    if (!this.validateForm()) return;

    console.log(this.leaveRequestForm.value);
    if (this.mode === 'ADD') {
      this.apiService.createLeaveRequest(this.leaveRequestForm.value).subscribe({
        next: res => {
          AlertUtil.showSuccessAlert('Leave request created successfully');
          this.onCancel();
        },
        error: err => {
          AlertUtil.showCommonErrorAlert(err);
        }
      });
    } else {
      this.apiService.updateLeaveRequest({id: this.leaveRequestId, ...this.leaveRequestForm.value}).subscribe({
        next: res => {
          AlertUtil.showSuccessAlert('Leave request updated successfully');
          this.onCancel();
        },
        error: err => {
          AlertUtil.showCommonErrorAlert(err);
        }
      });
    }
  }

  validateForm(): boolean {
    this.leaveRequestForm.markAllAsTouched();
    return this.leaveRequestForm.valid;
  }

}
