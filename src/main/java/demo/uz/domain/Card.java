package demo.uz.domain;

import com.google.gson.Gson;
import demo.uz.enums.Currency;
import demo.uz.model.CardDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class Card implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true)
    private String number;

    @Column(name = "expiry")
    private String expiry;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "balance")
    private Long balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "is_active", columnDefinition = "boolean default false")
    private boolean active;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    public static CardDto toDto(Card card){
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getId());
        cardDto.setNumber(card.getNumber());
        cardDto.setExpiry(card.getExpiry());
        cardDto.setUserId(card.getUserId());
        cardDto.setBalance(card.getBalance());
        cardDto.setCurrency(card.getCurrency());
        return cardDto;
    }
}
