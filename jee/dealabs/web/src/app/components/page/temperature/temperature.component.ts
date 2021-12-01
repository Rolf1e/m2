import {Component, Input, OnInit} from '@angular/core';
import {TemperatureDirection, TemperatureUpdate} from "../../../model/api/deal/temperature";
import {BasicHttpClient} from "../../../infra/http_client";
import {DEAL_TEMPERATURE_ROUTE, UPDATE_DEAL_TEMPERATURE_ROUTE} from "../../../config/path/api_paths";
import {LoginService} from "../../../services/login_service";
import {Observable} from "rxjs";
import {Objects} from "../../../utils/objects";

@Component({
  selector: 'app-temperature',
  templateUrl: './temperature.component.html',
  styleUrls: ['./temperature.component.css']
})
export class TemperatureComponent implements OnInit {

  @Input() temperature: number;
  @Input() dealId: number;
  @Input() nickname: string;

  constructor(private _rest: BasicHttpClient,
              private _loginService: LoginService) {
  }

  ngOnInit(): void {
    this._rest.get<number>(DEAL_TEMPERATURE_ROUTE.replace("{id}", String(this.dealId)))
      .subscribe(response => {
        if (200 === response.status) {
          this.temperature = Objects.requireNonNull(response.body);
        }
      });
  }

  public plusOneDegree(): void {
    this.updateDegree(TemperatureDirection.UP)
      .subscribe(() => this.ngOnInit());
  }

  public minusOneDegree(): void {
    this.updateDegree(TemperatureDirection.DOWN)
      .subscribe(() => this.ngOnInit());
  }

  private updateDegree(direction: TemperatureDirection): Observable<any> {
    return this._rest.put<any, TemperatureUpdate>(
      UPDATE_DEAL_TEMPERATURE_ROUTE,
      TemperatureUpdate.create(this.dealId, this.nickname, direction)
    );
  }

  public isLogged(): boolean {
    return this._loginService.isLogged();
  }
}
