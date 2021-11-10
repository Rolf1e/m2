import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DealShopComponent} from "./components/deal-shop/deal-shop.component";
import {DealDetailsComponent} from "./components/deal-details/deal-details.component";

const routes: Routes = [
  {path: 'deals/:dealId', component: DealDetailsComponent},
  {path: '', component: DealShopComponent},
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
