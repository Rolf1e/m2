export class NewDealDto {

  private title: string;
  private description: string;
  private priceOld: number;
  private priceNew: number;
  private shopName: string;
  private promoCode: string;
  private creatorId: number;
  private imgUrl: string;

  public static from(data: any): NewDealDto {
    if (undefined === data) {
      throw new Error("Failed to create new deal");
    }
    return new NewDealDto(
      data.title,
      data.description,
      data.priceOld,
      data.priceNew,
      data.shopName,
      data.promoCode,
      data.creatorId,
      data.imgUrl
    );
  }

  constructor(title: string, description: string, priceOld: number, priceNew: number, shopName: string, promoCode: string, creatorId: number, imgUrl: string) {
    this.title = title;
    this.description = description;
    this.priceOld = priceOld;
    this.priceNew = priceNew;
    this.shopName = shopName;
    this.promoCode = promoCode;
    this.creatorId = creatorId;
    this.imgUrl = imgUrl;
  }
}


