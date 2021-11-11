package con.rolfie.dealabs.model.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_temperature")
@Data
public class TemperatureDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private UserDo user;

    @ManyToOne
    @JoinColumn(name = "fk_deal")
    private DealDo deal;

}
