import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-leave-request-form',
  templateUrl: './leave-request-form.component.html',
  styleUrls: ['./leave-request-form.component.scss']
})
export class LeaveRequestFormComponent implements OnInit {

  mode: 'ADD' | 'EDIT';
  leaveRequestId: number;

  constructor(@Inject(MAT_DIALOG_DATA) private dialogData: {mode: 'ADD' | 'EDIT', leaveRequestId: number}) {
    this.mode = dialogData.mode;
    this.leaveRequestId = dialogData.leaveRequestId;
  }

  ngOnInit(): void {
    console.log(this.mode, this.leaveRequestId);
  }

}
