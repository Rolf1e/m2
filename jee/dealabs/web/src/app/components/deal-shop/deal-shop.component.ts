import {Component, OnInit} from '@angular/core';
import {Deal} from "../../model/api/deals";
import {from, Observable} from "rxjs";

@Component({
  selector: 'deal-shop',
  templateUrl: './deal-shop.component.html',
  styleUrls: ['./deal-shop.component.css']
})
export class DealShopComponent implements OnInit {

  private _deals: Observable<Deal[]>;

  constructor() {
    this._deals = DealShopComponent.fetchDeals();
  }

  ngOnInit(): void {
    this._deals = DealShopComponent.fetchDeals();
  }

  private static fetchDeals(): Observable<Deal[]> {
    let deals = [
      new Deal(
        "Disque dur externe 2.5\" Western Digital WD Element 5To",
        210,
        "Bob3404",
        new Date("2021-10-12"),
        "Le disque portable WD Elements doté d'une interface USB 3.0 offre une connectivité universelle, jusqu'à 5 To de capacité et assure des transferts de données ult",
        "Darty",
        "https://www.dealabs.com/visit/thread/2220678?API=true&user_id={{user_id}}&device_id=7377839",
        "https://static-pepper.dealabs.com/threads/raw/default/2220678_1/re/234x330/qt/60/2220678_1.jpg"
      ),
      new Deal(
        "Clavier MIDI AKAI Professional MPK Mini MK3",
        295,
        "NiveauZéro",
        new Date("2021-10-12"),
        "Prix sympa sur ce clavier midi au rapport Q/P imbattable. 95€ chez Thomann98€ chez Woodbrass\t[shortcode id=\"19014338\"/]Encore plus performant !Vous l'avez rêvé,",
        "Amazon",
        "https://www.dealabs.com/visit/thread/2220697?API=true&user_id={{user_id}}&device_id=7377839",
        "https://static-pepper.dealabs.com/threads/raw/default/2220697_1/re/234x330/qt/60/2220697_1.jpg"
      )
    ];
    return from([deals]);
  }


  get deals(): Observable<Deal[]> {
    return this._deals;
  }
}
