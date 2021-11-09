import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {DealShopComponent} from './components/deal-shop/deal-shop.component';

@NgModule({
  declarations: [
    AppComponent,
    DealShopComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
