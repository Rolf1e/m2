package con.rolfie.dealabs.model.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_deal")
@Data
public class DealDo {

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
    private Float priceOld;

    @Column(name = "price_new")
    private Float priceNew;

    @Column(name = "promo_code")
    private String promoCode;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_creator")
    private UserDo creator;

    @OneToMany(mappedBy = "deal")
    private List<TemperatureDo> temperatures;

    public Integer getTemperature() {
        return this.temperatures
                .stream()
                .mapToInt(TemperatureDo::getValue)
                .sum();
    }
}
