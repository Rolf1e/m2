import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-head-bar',
  templateUrl: './head-bar.component.html',
  styleUrls: ['./head-bar.component.css']
})
export class HeadBarComponent implements OnInit {

  private _title = 'ULCO - Dealabs';

  constructor() {
  }

  get title(): string {
    return this._title;
  }

  ngOnInit(): void {
  }

}
