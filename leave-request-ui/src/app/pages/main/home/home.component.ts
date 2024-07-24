import {Component, OnInit} from '@angular/core';
import {Constants} from "../../../constants/Constants";
import {AlertUtil} from "../../../utils/alert.util";
import {ApiService} from "../../../services/api.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  currentUser: any = null;

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.loadCurrentUser();
  }

  private loadCurrentUser(): void {
    this.apiService.getCurrentUser().subscribe({
      next: res => {
        this.currentUser = res;
      },
      error: err => {
        AlertUtil.showCommonErrorAlert(err);
      }
    });
  }

}
