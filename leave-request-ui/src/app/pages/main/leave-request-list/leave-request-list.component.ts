import { Component } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {LeaveRequestFormComponent} from "../leave-request-form/leave-request-form.component";

@Component({
  selector: 'app-leave-request-list',
  templateUrl: './leave-request-list.component.html',
  styleUrls: ['./leave-request-list.component.scss']
})
export class LeaveRequestListComponent {

  displayedColumns: string[] = ['id', 'leaveType', 'startDate', 'endDate', 'reason', 'action'];
  dataSource = [
    {id: 1, leaveType: 'CASUAL', startDate: new Date(), endDate: new Date(), reason: 'leave comment here'},
    {id: 2, leaveType: 'CASUAL', startDate: new Date(), endDate: new Date(), reason: 'leave comment here'},
  ];

  constructor(private matDialog: MatDialog) {

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
    console.log('delete', dataRow.id)
  }

  loadLeaveRequests(): void {
    console.log('load leave reqs')
  }

}
