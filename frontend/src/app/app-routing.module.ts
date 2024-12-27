import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';

const routes: Routes = [
  {
    path:'index',
    component:IndexComponent
  },
  {
    path:'index#:section',
    component:IndexComponent
  },
  {
    path:'**',
    redirectTo:'/index',
    pathMatch:'full'
  }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
