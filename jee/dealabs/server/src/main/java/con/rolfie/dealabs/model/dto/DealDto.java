package con.rolfie.dealabs.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import con.rolfie.dealabs.model.database.entity.DealDo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DealDto {

    private final Long id;
    private final String title;
    private final String shopName;
    private final String shopLink;
    private final Integer temperature;
    private final String creator;
    private final LocalDateTime date;
    private final String imgUrl;
    private final String description;

    @JsonCreator
    public static DealDto from(final DealDo deal) {
        return DealDto.create(
                deal.getId(),
                deal.getTitle(),
                deal.getShopName(),
                deal.getShopLink(),
                deal.getTemperature(),
                Objects.requireNonNull(deal.getCreator()).getFirstName(),
                deal.getDate(),
                deal.getImgUrl(),
                deal.getDescription()
        );
    }

    @JsonCreator()
    public static DealDto create(@JsonProperty("id") final Long id,
                                 @JsonProperty("title") final String title,
                                 @JsonProperty("shop_name") final String shopName,
                                 @JsonProperty("shop_link") final String shopLink,
                                 @JsonProperty("temperature") final Integer temperature,
                                 @JsonProperty("creator") final String creator,
                                 @JsonProperty("date") final LocalDateTime date,
                                 @JsonProperty("img_url") final String imgUrl,
                                 @JsonProperty("description") final String description) {

        return new DealDto(id, title, shopName, shopLink, temperature, creator, date, imgUrl, description);
    }

}
