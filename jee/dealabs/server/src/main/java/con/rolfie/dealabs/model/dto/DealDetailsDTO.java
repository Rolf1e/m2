package con.rolfie.dealabs.model.dto;

import con.rolfie.dealabs.model.database.entity.DealDO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DealDetailsDTO {

    private final int priceOld;
    private final int priceNew;
    private final String promoCode;

    public static DealDetailsDTO from(final DealDO deal) {
        return new DealDetailsDTO(deal.getPriceOld(), deal.getPriceNew(), deal.getPromoCode());
    }
}
