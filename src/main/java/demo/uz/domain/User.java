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
@Table(name = "users")
public class User implements Serializable {

    @Transient
    public final String sequenceName = "user_id_seq";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequenceName)
    @SequenceGenerator(name = sequenceName, sequenceName = sequenceName, allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "address")
    private String address;

    @Column(name = "gender", columnDefinition = "boolean default false")
    private boolean gender;

    @Column(name = "is_active", columnDefinition = "boolean default false")
    private boolean isActive;
}
