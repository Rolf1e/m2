import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {User} from "../../../model/api/user/user";
import {LOGIN_USER_ROUTE} from "../../../config/path/api_paths";
import {Login} from "../../../model/api/user/login";
import {CookieService} from "ngx-cookie-service";
import {BasicAuthService} from "../../../services/auth_service";
import {Credentials} from "../../../infra/auth";

@Component({
    selector: 'app-login-form',
    templateUrl: './login-form.component.html',
    styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

    public static readonly COOKIE_BASIC_CREDENTIALS = 'basic-credential';

    loginUserForm = this._formBuilder.group({
        'nickname': '',
        'password': '',
    });

    constructor(private _formBuilder: FormBuilder,
                private _rest: HttpClient,
                private _cookiesService: CookieService,
                private _authService: BasicAuthService) {
    }

    ngOnInit(): void {
    }

    onSubmit(): void {
        let data = this.loginUserForm.value;

        this._rest.post<User>(LOGIN_USER_ROUTE, Login.from(data), {observe: 'response'})
            .subscribe(_ => this.onSuccess());
    }

    private onSuccess() {
        let data = this.loginUserForm.value;
        let authorization = this._authService.getAuthorization(Credentials.create(data.nickname, data.password));
        this._cookiesService.set(LoginFormComponent.COOKIE_BASIC_CREDENTIALS, authorization);
    }
}
