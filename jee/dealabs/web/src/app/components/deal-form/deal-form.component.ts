import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {CREATE_DEAL_ROUTE, DEAL_ROUTE} from "../../config/path/api_paths";
import {Router} from "@angular/router";
import {Deal} from "../../model/api/deals";
import {NewDealDto} from "../../model/api/new_deal";

@Component({
  selector: 'deal-form',
  templateUrl: './deal-form.component.html',
  styleUrls: ['./deal-form.component.css']
})
export class DealFormComponent implements OnInit {

  createDealForm = this._formBuilder.group({
    'title': '',
    'description': '',
    'priceOld': '',
    'priceNew': '',
    'shopName': '',
    'promoCode': '',
    'creator': '',
    'imgUrl': ''
  });

  constructor(private _formBuilder: FormBuilder,
              private _rest: HttpClient,
              private _router: Router) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this._rest.post<Deal>(CREATE_DEAL_ROUTE, NewDealDto.from(this.createDealForm.value))
      .subscribe(deal => this.navigateOnSuccess(deal));
  }

  private navigateOnSuccess(deal: Deal): void {
    this._router.navigate(['deals', deal.id])
      .catch(err => console.log(err));
  }

}
