import {Component, OnInit} from '@angular/core';
import {Deal} from "../../../model/api/deal/deals";
import {Observable} from "rxjs";
import {DEAL_ROUTE, DEALS_DETAILS_ROUTE} from "../../../config/path/api_paths";
import {DealDetails} from "../../../model/api/deal/details";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'deal-details',
  templateUrl: './deal-details.component.html',
  styleUrls: ['./deal-details.component.css']
})
export class DealDetailsComponent implements OnInit {

  private _deal: Deal;
  private _details: Observable<DealDetails>;

  constructor(private _rest: HttpClient,
              private _route: ActivatedRoute) {

    _route.url.subscribe(route => {
        let id = route.pop()?.path;
        // @ts-ignore
      this.fetchDeal(+id)
          .subscribe(deal => this._deal = deal);
        // @ts-ignore
      this._details = this.fetchDetails(+id);
      }
    );

  }

  ngOnInit(): void {
    this._deal = history.state.data;
    this._details = this.fetchDetails(this._deal.id);
  }

  private fetchDetails(id: number): Observable<DealDetails> {
    return this._rest.get<DealDetails>(DEALS_DETAILS_ROUTE.replace("{id}", String(id)));
  }

  private fetchDeal(id: number): Observable<Deal> {
    return this._rest.get<Deal>(DEAL_ROUTE.replace("{id}", String(id)));
  }

  get details(): Observable<DealDetails> {
    return this._details;
  }

  get deal(): Deal {
    return this._deal;
  }
}
