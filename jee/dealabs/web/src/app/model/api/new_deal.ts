export class NewDealDto {

  private title: string;
  private description: string;
  private priceOld: number;
  private priceNew: number;
  private shopName: string;
  private promoCode: string;
  private creator: string;
  private imgUrl: string;

  constructor(title: string, description: string, priceOld: number, priceNew: number, shopName: string, promoCode: string, creator: string, imgUrl: string) {
    this.title = title;
    this.description = description;
    this.priceOld = priceOld;
    this.priceNew = priceNew;
    this.shopName = shopName;
    this.promoCode = promoCode;
    this.creator = creator;
    this.imgUrl = imgUrl;
  }
}


