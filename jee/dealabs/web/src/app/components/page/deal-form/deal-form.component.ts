import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {CREATE_DEAL_ROUTE} from "../../../config/path/api_paths";
import {Router} from "@angular/router";
import {Deal} from "../../../model/api/deals";
import {NewDealDto} from "../../../model/api/new_deal";
import {BasicHttpClient} from "../../../infra/http_client";
import {Credentials} from "../../../infra/auth";
import {Objects} from "../../../utils/objects";

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
        'creatorId': '',
        'imgUrl': ''
    });

    constructor(private _formBuilder: FormBuilder,
                private _rest: BasicHttpClient,
                private _router: Router) {
    }

    ngOnInit(): void {
    }

    onSubmit(): void {
        this._rest.post<Deal, NewDealDto>(CREATE_DEAL_ROUTE, NewDealDto.from(this.createDealForm.value), Credentials.create('tigran', 'a'))
            .subscribe(response => this.navigateOnSuccess(Objects.requireNonNull(response.headers.get('Location'))));
    }

    private navigateOnSuccess(url: string): void {
        this._router.navigateByUrl(url)
            .catch(err => console.log(err));
    }

}
