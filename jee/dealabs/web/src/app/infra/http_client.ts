import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {BasicAuthService} from "../services/auth_service";
import {Injectable} from "@angular/core";
import {CookieService} from "ngx-cookie-service";
import {LoginFormComponent} from "../components/page/login-form/login-form.component";

@Injectable({
    providedIn: 'root',
})
export class BasicHttpClient {

    constructor(private _rest: HttpClient,
                private _authService: BasicAuthService,
                private _cookiesService: CookieService) {
    }

    public get<T>(url: string): Observable<HttpResponse<T>> {
        return this._rest.get<T>(
            url,
            {
                headers: this.getHeaders(),
                observe: 'response',
            }
        );
    }

    public post<T, B>(url: string,
                      body: B): Observable<HttpResponse<T>> {
        return this._rest.post<T>(
            url,
            body,
            {
                headers: this.getHeaders(),
                observe: 'response',
            }
        );
    }

    private getHeaders(): HttpHeaders {
        return new HttpHeaders(
            {
                'Authorization': this._cookiesService.get(LoginFormComponent.COOKIE_BASIC_CREDENTIALS),
            }
        );
    }
}
