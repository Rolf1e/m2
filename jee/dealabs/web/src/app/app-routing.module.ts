import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DealShopComponent} from "./components/deal-shop/deal-shop.component";
import {DealDetailsComponent} from "./components/deal-details/deal-details.component";
import {DealFormComponent} from "./components/deal-form/deal-form.component";

const routes: Routes = [
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
