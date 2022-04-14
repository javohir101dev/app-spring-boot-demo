package demo.uz.model;

import demo.uz.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OperationDto {

    private Long id;

    private String operationCode;

    private Long senderCardNumber;
    private Long senderAmount;
    private Currency senderCurrency;

    private Long receiverCardNumber;
    private Long receiverAmount;
    private Currency receiverCurrency;

    private Long commission;
    private Short commissionRate;
    private LocalDateTime operationDate;

}
