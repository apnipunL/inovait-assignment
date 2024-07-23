import {CanActivateFn, Router} from '@angular/router';
import {Constants} from "../constants/Constants";
import {inject} from "@angular/core";

export const authGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);

  if (!localStorage.getItem(Constants.LOCAL_STORAGE_KEY_ACCESS_TOKEN)) {
    router.navigate(['/login']);
    return false;
  }

  return true;
};
