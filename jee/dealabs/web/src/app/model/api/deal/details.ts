export class DealDetails {

  private readonly _priceOld: number;
  private readonly _priceNew: number;
  private readonly _promoCode: number;

  constructor(priceOld: number, priceNew: number, promoCode: number) {
    this._priceOld = priceOld;
    this._priceNew = priceNew;
    this._promoCode = promoCode;
  }

  get priceOld(): number {
    return this._priceOld;
  }

  get priceNew(): number {
    return this._priceNew;
  }

  get promoCode(): number {
    return this._promoCode;
  }
}
