package demo.uz.domain;

import demo.uz.enums.Currency;
import demo.uz.enums.OperationStatus;
import demo.uz.helper.Utils;
import demo.uz.model.OperationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operations")
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_status")
    private OperationStatus operationStatus;

    @Column(name = "operation_code")
    private String operationCode;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Card sender;
    @Column(name = "sender_id", insertable = false, updatable = false)
    private Long sender_id;

    @Column(name = "sender_amount")
    private Long senderAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "sender_currency")
    private Currency senderCurrency;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Card receiver;
    @Column(name = "receiver_id", insertable = false, updatable = false)
    private Long receiver_id;

    @Column(name = "receiver_amount")
    private Long receiverAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "receiver_currency")
    private Currency receiverCurrency;

    /**
     * Commission amount
     */
    @Column(name = "commission")
    private Long commission;

    /**
     * Commission rate
     */
    @Column(name = "commission_rate")
    private Short commissionRate;

    @Column(name = "operation_date", columnDefinition = "timestamp default now()")
    private LocalDateTime operationDate = LocalDateTime.now();

}
