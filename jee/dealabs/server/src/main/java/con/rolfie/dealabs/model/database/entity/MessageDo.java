package con.rolfie.dealabs.model.database.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_message")
public class MessageDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_creator")
    private UserDo author;

    @ManyToOne
    @JoinColumn(name = "fk_deal")
    private DealDo deal;

    @Column(name = "content")
    private String content;

    @Column(name = "time")
    private LocalDateTime time;
}
