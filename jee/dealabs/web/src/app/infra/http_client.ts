import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Credentials} from "./auth";
import {BasicAuthService} from "../services/auth_service";
import {Injectable} from "@angular/core";

@Injectable({
    providedIn: 'root',
})
export class BasicHttpClient {

    constructor(private _rest: HttpClient,
                private _authService: BasicAuthService) {
    }

    public get<T>(url: string,
                  credentials: Credentials): Observable<HttpResponse<T>> {
        return this._rest.get<T>(
            url,
            {
                headers: this.getHeaders(credentials),
                observe: 'response',
            }
        );
    }

    public post<T, B>(url: string,
                      body: B,
                      credentials: Credentials): Observable<HttpResponse<T>> {
        return this._rest.post<T>(
            url,
            body,
            {
                headers: this.getHeaders(credentials),
                observe: 'response',
            }
        );
    }

    private getHeaders(credentials: Credentials): HttpHeaders {
        return new HttpHeaders(
            {
                'Authorization': this._authService.getAuthorization(credentials),
            }
        );
    }
}
