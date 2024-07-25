import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {LeaveRequestFormComponent} from "../leave-request-form/leave-request-form.component";
import {Constants} from "../../../constants/Constants";
import {AlertUtil} from "../../../utils/alert.util";
import {ApiService} from "../../../services/api.service";

@Component({
  selector: 'app-leave-request-list',
  templateUrl: './leave-request-list.component.html',
  styleUrls: ['./leave-request-list.component.scss']
})
export class LeaveRequestListComponent implements OnInit{

  displayedColumns: string[] = ['id', 'leaveType', 'startDate', 'endDate', 'reason', 'action'];
  dataSource = [];

  constructor(
    private matDialog: MatDialog,
    private apiService: ApiService,
  ) { }

  ngOnInit(): void {
    this.loadLeaveRequests();
  }

  onNewLeaveRequest(): void {
    const dialogRef = this.matDialog.open(LeaveRequestFormComponent, {
      data: {
        mode: 'ADD'
      }
    });

    dialogRef.afterClosed().subscribe(_ => {
      this.loadLeaveRequests();
    });
  }

  onEditLeaveRequest(dataRow: any): void {
    const dialogRef = this.matDialog.open(LeaveRequestFormComponent, {
      data: {
        mode: 'EDIT',
        leaveRequestId: dataRow.id
      }
    });

    dialogRef.afterClosed().subscribe(_ => {
      this.loadLeaveRequests();
    });
  }

  onDeleteLeaveRequest(dataRow: any): void {
    this.apiService.deleteLeaveRequest(dataRow.id).subscribe({
      next: res => {
        AlertUtil.showSuccessAlert('Leave request deleted successfully');
        this.loadLeaveRequests();
      },
      error: err => {
        AlertUtil.showCommonErrorAlert(err);
      }
    });
  }

  loadLeaveRequests(): void {
    this.apiService.getAllLeaveRequest().subscribe({
      next: res => {
        this.dataSource = res;
      },
      error: err => {
        AlertUtil.showCommonErrorAlert(err);
      }
    });
  }

}
