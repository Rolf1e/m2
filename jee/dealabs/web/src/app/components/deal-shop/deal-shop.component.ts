import {Component, OnInit} from '@angular/core';
import {Deal} from "../../model/api/deals";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {DEALS_ROUTE} from "../../config/path/api_paths";

@Component({
  selector: 'deal-shop',
  templateUrl: './deal-shop.component.html',
  styleUrls: ['./deal-shop.component.css']
})
export class DealShopComponent implements OnInit {

  private _deals: Observable<Deal[]>;

  constructor(private _rest: HttpClient) {
    this._deals = this.fetchDeals();
  }

  ngOnInit(): void {
    this._deals = this.fetchDeals();
  }

  public fetchDeals(): Observable<Deal[]> {
    return this._rest.get<Deal[]>(DEALS_ROUTE);
  }

  get deals(): Observable<Deal[]> {
    return this._deals;
  }
}
