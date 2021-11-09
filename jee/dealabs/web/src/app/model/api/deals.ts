export class Deal {

  private readonly _title: string;
  private readonly _temperature: number;
  private readonly _creator: string;
  private readonly _date: Date;
  private readonly _description: string;
  private readonly _shopName: string;
  private readonly _shopLink: string;
  private readonly _imgUrl: string;

  constructor(title: string, temperature: number, creator: string, date: Date, description: string, shopName: string, shopLink: string, imgUrl: string) {
    this._title = title;
    this._temperature = temperature;
    this._creator = creator;
    this._date = date;
    this._description = description;
    this._shopName = shopName;
    this._shopLink = shopLink;
    this._imgUrl = imgUrl;
  }


  get title(): string {
    return this._title;
  }

  get temperature(): number {
    return this._temperature;
  }

  get creator(): string {
    return this._creator;
  }

  get date(): Date {
    return this._date;
  }

  get description(): string {
    return this._description;
  }

  get shopName(): string {
    return this._shopName;
  }

  get shopLink(): string {
    return this._shopLink;
  }

  get imgUrl(): string {
    return this._imgUrl;
  }
}
