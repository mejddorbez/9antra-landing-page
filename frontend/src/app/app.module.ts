import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { API, BaseURL} from './shared/constants';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { IndexComponent } from './index/index.component';
import { HttpClientModule } from '@angular/common/http';
import { OffreService } from './services/offre.service';
import { CommonModule } from '@angular/common';
import { CrudComponent } from './crud/crud.component';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    IndexComponent,
    CrudComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [OffreService, 
    {provide:'BaseURL', useValue: BaseURL}, 
    {provide:'API', useValue: API}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
