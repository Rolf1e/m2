import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {DealShopComponent} from './components/deal-shop/deal-shop.component';
import {HttpClientModule} from "@angular/common/http";
import {DealDetailsComponent} from './components/deal-details/deal-details.component';
import {AppRoutingModule} from "./app-routing.module";
import {DealFormComponent} from './components/deal-form/deal-form.component';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    DealShopComponent,
    DealDetailsComponent,
    DealFormComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule {
}
