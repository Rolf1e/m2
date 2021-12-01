export enum TemperatureDirection {
  UP, DOWN
}

export class TemperatureUpdate {

  private dealId: number;
  private nickname: string;
  private direction: TemperatureDirection;

  public static create(dealId: number,
                       nickname: string,
                       direction: TemperatureDirection): TemperatureUpdate {
    return new TemperatureUpdate(dealId, nickname, direction);
  }

  private constructor(dealId: number, nickname: string, direction: TemperatureDirection) {
    this.dealId = dealId;
    this.nickname = nickname;
    this.direction = direction;
  }
}
