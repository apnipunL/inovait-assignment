import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignUpComponent} from "./pages/sign-up/sign-up.component";
import {LoginComponent} from "./pages/login/login.component";
import {MainLayoutComponent} from "./pages/main-layout/main-layout.component";
import {HomeComponent} from "./pages/main/home/home.component";
import {LeaveRequestFormComponent} from "./pages/main/leave-request-form/leave-request-form.component";
import {LeaveRequestListComponent} from "./pages/main/leave-request-list/leave-request-list.component";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'main'
  },
  {
    path: 'main',
    component: MainLayoutComponent,
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'home',
      },
      {
        path: 'home',
        component: HomeComponent,
      },
      {
        path: 'leave-request-list',
        component: LeaveRequestListComponent,
      },
    ]
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'sign-up',
    component: SignUpComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
