import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login_service";

@Component({
    selector: 'app-head-bar',
    templateUrl: './head-bar.component.html',
    styleUrls: ['./head-bar.component.css']
})
export class HeadBarComponent implements OnInit {

    private _title = 'ULCO - Dealabs';

    constructor(private _loginService: LoginService) {
    }

    get title(): string {
        return this._title;
    }

    ngOnInit(): void {
    }

    public isLogged(): boolean {
        return this._loginService.isLogged();
    }

    public getNickName(): string {
        return this._loginService.nickname;
    }

    public disconnect() {
        this._loginService.disconnect();
    }

}
