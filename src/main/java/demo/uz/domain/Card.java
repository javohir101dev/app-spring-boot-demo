package demo.uz.domain;

import com.google.gson.Gson;
import demo.uz.enums.Currency;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Entity
@Table(name = "cards")
public class Card implements Serializable {

    @Transient
    public final String sequenceName = "card_id_seq";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequenceName)
    @SequenceGenerator(name = sequenceName, sequenceName = sequenceName, allocationSize = 1)
    private Long id;

    @Column(name = "number", unique = true)
    private String number;

    @Column(name = "expiry")
    private String expiry;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "balance")
    private Long balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "is_active", columnDefinition = "boolean default false")
    private boolean isActive;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
