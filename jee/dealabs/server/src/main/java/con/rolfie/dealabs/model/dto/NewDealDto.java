package con.rolfie.dealabs.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NewDealDto {

    private final String title;
    private final String description;
    private final Float priceOld;
    private final Float priceNew;
    private final String shopName;
    private final String promoCode;
    private final Long creatorId;
    private final String imgUrl;

    @JsonCreator()
    public static NewDealDto create(@JsonProperty("title") final String title,
                                    @JsonProperty("description") final String description,
                                    @JsonProperty("priceOld") final Float priceOld,
                                    @JsonProperty("priceNew") final Float priceNew,
                                    @JsonProperty("shopName") final String shopName,
                                    @JsonProperty("promoCode") final String promoCode,
                                    @JsonProperty("creatorId") final Long creatorId,
                                    @JsonProperty("imgUrl") final String imgUrl) {
        return new NewDealDto(title, description, priceOld, priceNew, shopName, promoCode, creatorId, imgUrl);
    }

}
