import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-login-form',
    templateUrl: './login-form.component.html',
    styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

    createUserForm = this._formBuilder.group({

    });

    constructor(private _formBuilder: FormBuilder,
                private _rest: HttpClient) {
    }

    ngOnInit(): void {
    }

}
