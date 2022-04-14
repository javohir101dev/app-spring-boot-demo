package demo.uz.model;

import demo.uz.enums.Currency;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CardDto {

    private Long id;

    private String number;

    private String expiry;

    private Long userId;

    private Long balance;

    private Currency currency;


}
