import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { CrudComponent } from './crud/crud.component';

const routes: Routes = [
  {
    path:'index',
    component:IndexComponent
  },
  {
    path:'crud',
    component:CrudComponent
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
