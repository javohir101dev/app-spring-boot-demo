package demo.uz.domain;

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
@Table(name = "operation_codes")
public class OperationCode implements Serializable {

    @Transient
    public final String sequenceName = "operation_code_id_seq";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequenceName)
    @SequenceGenerator(name = sequenceName, sequenceName = sequenceName, allocationSize = 1)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "exchange_currency")
    private Long exchangeCurrency;

    @Column(name = "is_upper")
    private Boolean isUpper;


}
