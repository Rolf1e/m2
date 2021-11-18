import {Component, OnInit} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {LoginFormComponent} from "../page/login-form/login-form.component";

@Component({
    selector: 'app-head-bar',
    templateUrl: './head-bar.component.html',
    styleUrls: ['./head-bar.component.css']
})
export class HeadBarComponent implements OnInit {

    private _title = 'ULCO - Dealabs';

    constructor(private _cookiesService: CookieService) {
    }

    get title(): string {
        return this._title;
    }

    ngOnInit(): void {
    }

    public isLogged(): boolean {
        return this._cookiesService.check(LoginFormComponent.COOKIE_BASIC_CREDENTIALS);
    }

    public disconnect() {
        this._cookiesService.delete(LoginFormComponent.COOKIE_BASIC_CREDENTIALS);

    }

}
