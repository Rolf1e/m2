import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {User} from "../../../model/api/user/user";
import {CREATE_USER_ROUTE} from "../../../config/path/api_paths";
import {NewUser} from "../../../model/api/user/new_user";
import {Router} from "@angular/router";
import {Objects} from "../../../utils/objects";

@Component({
    selector: 'app-register-form',
    templateUrl: './register-form.component.html',
    styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

    createUserForm = this._formBuilder.group({
        'nickname': '',
        'first_name': '',
        'last_name': '',
        'password': '',
        'password2': ''
    });

    constructor(private _formBuilder: FormBuilder,
                private _rest: HttpClient,
                private _router: Router) {
    }

    ngOnInit(): void {
    }

    onSubmit(): void {
        let data = this.createUserForm.value;
        if (data.password !== data.password2) {
            throw new Error("Passwords are different");
        }

        this._rest.post<User>(CREATE_USER_ROUTE, NewUser.from(data), {observe: 'response'})
            .subscribe(response => this.navigateOnSuccess(Objects.requireNonNull(response.headers.get('Location'))));
    }

    private navigateOnSuccess(url: string): void {
        this._router.navigateByUrl(url)
            .catch(err => console.log(err));
    }

}
