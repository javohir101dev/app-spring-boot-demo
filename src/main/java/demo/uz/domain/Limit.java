package demo.uz.domain;

import demo.uz.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "limits")
public class Limit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation_code", unique = true)
    private String operationCode;

    @Column(name = "min_limit")
    private Long minLimit;

    @Column(name = "max_limit")
    private Long maxLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency = Currency.UZS;
}
