import {Injectable} from "@angular/core";
import {CookieService} from "ngx-cookie-service";
import {LoginFormComponent} from "../components/page/login-form/login-form.component";
import {Credentials} from "../infra/auth";
import {BasicAuthService} from "./auth_service";

@Injectable({
    providedIn: 'root',
})
export class LoginService {

    private _nickname: string;

    constructor(private _cookieService: CookieService,
                private _authService: BasicAuthService) {
    }

    public login(nickname: string, password: string) {
        let authorization = this._authService.getAuthorization(Credentials.create(nickname, password));
        this._cookieService.set(LoginFormComponent.COOKIE_BASIC_CREDENTIALS, authorization);
        this._nickname = nickname;
    }

    public isLogged(): boolean {
        return this._cookieService.check(LoginFormComponent.COOKIE_BASIC_CREDENTIALS);
    }

    public disconnect() {
        this._cookieService.delete(LoginFormComponent.COOKIE_BASIC_CREDENTIALS);
    }

    get nickname(): string {
        return this._nickname;
    }
}
