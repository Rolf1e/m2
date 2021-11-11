package con.rolfie.dealabs.model.dto;

import con.rolfie.dealabs.model.database.entity.DealDo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DealDetailsDto {

    private final float priceOld;
    private final float priceNew;
    private final String promoCode;

    public static DealDetailsDto from(final DealDo deal) {
        return new DealDetailsDto(deal.getPriceOld(), deal.getPriceNew(), deal.getPromoCode());
    }

}
