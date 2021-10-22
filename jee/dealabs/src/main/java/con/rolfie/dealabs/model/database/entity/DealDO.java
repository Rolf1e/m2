package con.rolfie.dealabs.model.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_deal")
@Data
public class DealDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_link")
    private String shopLink;

    @Column(name = "price_old")
    private Integer priceOld;

    @Column(name = "price_new")
    private Integer priceNew;

    @Column(name = "promo_code")
    private String promoCode;

    @Column(name = "temperature")
    private Integer temperature;

    @Column(name = "creator")
    private String creator;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "description")
    private String description;

}
