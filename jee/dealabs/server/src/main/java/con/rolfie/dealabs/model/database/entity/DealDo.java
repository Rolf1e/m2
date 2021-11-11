package con.rolfie.dealabs.model.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbl_deal")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Integer priceOld;

    @Column(name = "price_new")
    private Integer priceNew;

    @Column(name = "promo_code")
    private String promoCode;

    @Column(name = "date")
    private LocalDate date;

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
