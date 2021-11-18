import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {DealShopComponent} from './components/page/deal-shop/deal-shop.component';
import {HttpClientModule} from "@angular/common/http";
import {DealDetailsComponent} from './components/page/deal-details/deal-details.component';
import {AppRoutingModule} from "./app-routing.module";
import {DealFormComponent} from './components/page/deal-form/deal-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import {LoginFormComponent} from './components/page/login-form/login-form.component';
import {RegisterFormComponent} from './components/page/register-form/register-form.component';
import {HeadBarComponent} from './components/head-bar/head-bar.component';
import {UserDetailsComponent} from './components/page/user-details/user-details.component';
import {CookieService} from "ngx-cookie-service";

@NgModule({
    declarations: [
        AppComponent,
        DealShopComponent,
        DealDetailsComponent,
        DealFormComponent,
        LoginFormComponent,
        RegisterFormComponent,
        HeadBarComponent,
        UserDetailsComponent,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        ReactiveFormsModule
    ],
    providers: [CookieService],
    bootstrap: [AppComponent]
})

export class AppModule {
}
