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
@Table(name = "commissions")
public class Commission implements Serializable {

    @Transient
    public final String sequenceName = "commission_id_seq";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequenceName)
    @SequenceGenerator(name = sequenceName, sequenceName = sequenceName, allocationSize = 1)
    private Long id;

    @Column(name = "operation_code", unique = true)
    private String operationCode;

    @Column(name = "rate")
    private Short rate;

    @Column(name = "is_upper", columnDefinition = "boolean default false")
    private boolean isUpper;
}
