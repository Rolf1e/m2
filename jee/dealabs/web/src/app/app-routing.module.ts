import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DealShopComponent} from "./components/page/deal-shop/deal-shop.component";
import {DealDetailsComponent} from "./components/page/deal-details/deal-details.component";
import {DealFormComponent} from "./components/page/deal-form/deal-form.component";
import {LoginFormComponent} from "./components/page/login-form/login-form.component";
import {RegisterFormComponent} from "./components/page/register-form/register-form.component";
import {UserDetailsComponent} from "./components/page/user-details/user-details.component";

const routes: Routes = [

    // login routes
    {path: 'login', component: LoginFormComponent},
    {path: 'register', component: RegisterFormComponent},
    {path: 'users/:id', component: UserDetailsComponent},


    // deal routes
    {path: 'deals/create', component: DealFormComponent},
    {path: 'deals/:id', component: DealDetailsComponent},
    {path: '', component: DealShopComponent},
];

@NgModule({
    declarations: [],
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
