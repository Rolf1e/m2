import {Credentials} from "../infra/auth";
import {Injectable} from "@angular/core";

export interface AuthService {
    getAuthorization(credentials: Credentials): string;
}

@Injectable({
    providedIn: 'root',
})
export class BasicAuthService implements AuthService {

    public getAuthorization(credentials: Credentials): string {
        return 'Basic ' + btoa(credentials.name + ':' + credentials.password);
    }

}
