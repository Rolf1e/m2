import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {DealShopComponent} from './components/deal-shop/deal-shop.component';
import {HttpClientModule} from "@angular/common/http";
import {DealDetailsComponent} from './components/deal-details/deal-details.component';
import {AppRoutingModule} from "./app-routing.module";

@NgModule({
  declarations: [
    AppComponent,
    DealShopComponent,
    DealDetailsComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule {
}
