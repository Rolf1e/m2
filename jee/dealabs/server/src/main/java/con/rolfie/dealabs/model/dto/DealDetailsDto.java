package con.rolfie.dealabs.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import con.rolfie.dealabs.model.database.entity.DealDo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DealDetailsDto {

    private final int priceOld;
    private final int priceNew;
    private final String promoCode;

    public static DealDetailsDto from(final DealDo deal) {
        return new DealDetailsDto(deal.getPriceOld(), deal.getPriceNew(), deal.getPromoCode());
    }

    @JsonCreator()
    public static DealDetailsDto create(@JsonProperty("priceOld") final int priceOld,
                                        @JsonProperty("priceNew") final int priceNew,
                                        @JsonProperty("promoCode") final String promoCode) {
        return new DealDetailsDto(priceOld, priceNew, promoCode);
    }
}
