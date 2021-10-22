package con.rolfie.dealabs.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import con.rolfie.dealabs.model.database.entity.DealDO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DealDTO {

    private final String title;
    private final String shopName;
    private final String shopLink;
    private final Integer temperature;
    private final String creator;
    private final LocalDate date;
    private final String imgUrl;
    private final String description;

    public static DealDTO from(final DealDO deal) {
        return DealDTO.create(
                deal.getTitle(),
                deal.getShopName(),
                deal.getShopLink(),
                deal.getTemperature(),
                deal.getCreator(),
                deal.getDate(),
                deal.getImgUrl(),
                deal.getDescription()
        );
    }

    @JsonCreator()
    public static DealDTO create(@JsonProperty("title") final String title,
                                 @JsonProperty("shop_name") final String shopName,
                                 @JsonProperty("shop_link") final String shopLink,
                                 @JsonProperty("temperature") final Integer temperature,
                                 @JsonProperty("creator") final String creator,
                                 @JsonProperty("date") final LocalDate date,
                                 @JsonProperty("img_url") final String imgUrl,
                                 @JsonProperty("description") final String description) {

        return new DealDTO(title, shopName, shopLink, temperature, creator, date, imgUrl, description);
    }

}
