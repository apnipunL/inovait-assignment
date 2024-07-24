import Swal from "sweetalert2";

export class AlertUtil {

  public static showErrorAlert(message: string): void {
    Swal.fire({title: 'Error!', text: message, icon: 'error'});
  }

  public static showCommonErrorAlert(err: any): void {
    if (err && err.status) {
      this.showErrorAlert(err.error?.message ?? 'Something went wrong. Please retry!');
    } else {
      this.showErrorAlert('Something went wrong. Please retry!');
    }
  }

}
