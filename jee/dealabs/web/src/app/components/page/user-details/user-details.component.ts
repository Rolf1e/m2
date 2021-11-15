import {Component, OnInit} from '@angular/core';
import {map, Observable} from "rxjs";
import {User} from "../../../model/api/user";
import {BasicHttpClient} from "../../../infra/http_client";
import {ActivatedRoute} from "@angular/router";
import {USERS_DETAILS_ROUTE} from "../../../config/path/api_paths";
import {Credentials} from "../../../infra/auth";
import {Objects} from "../../../utils/objects";

@Component({
    selector: 'app-user-details',
    templateUrl: './user-details.component.html',
    styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

    private _user: Observable<User>;

    constructor(private _rest: BasicHttpClient,
                private _route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this._route.url.subscribe(
            route => {
                let id = route.pop()?.path;
                // @ts-ignore
                this._user = this.fetchUser(+id);
            }
        );
    }

    get user(): Observable<User> {
        return this._user;
    }

    private fetchUser(id: number): Observable<User> {
        return this._rest.get<User>(
            USERS_DETAILS_ROUTE.replace('{id}', String(id)),
            Credentials.create('tigran', 'a') // TODO read from cache
        ).pipe(map(response => Objects.requireNonNull(response.body)));
    }

}
