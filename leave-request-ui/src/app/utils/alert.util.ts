import Swal from "sweetalert2";

export class AlertUtil {

  public static showErrorAlert(message: string): void {
    Swal.fire({title: 'Error!', text: message, icon: 'error'});
  }

  public static showCommonErrorAlert(err: any): void {
    if (err && err.status) {
      switch (err.status) {
        default: this.showErrorAlert('Something went wrong. Please retry!'); break;
      }
    } else {
      this.showErrorAlert('Something went wrong. Please retry!');
    }
  }

}
