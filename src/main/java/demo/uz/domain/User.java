package demo.uz.domain;

import demo.uz.model.UserCrudDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users" , uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
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

    @Column(name = "birthday", columnDefinition = "timestamp default null")
    private LocalDate birthday;

    public static User toUser(UserCrudDto userCrudDto){
        User user = new User();
        user.setFirstName(userCrudDto.getFirstName());
        user.setLastName(userCrudDto.getLastName());
        user.setUsername(userCrudDto.getUsername());
        user.setAddress(userCrudDto.getAddress());
        user.setActive(true);
        user.setBirthday(userCrudDto.getBirthday());
        user.setGender(userCrudDto.isGender());
        return user;
    }
    public static User toUser(User user, UserCrudDto userCrudDto){
        user.setFirstName(userCrudDto.getFirstName() != null ? userCrudDto.getFirstName() : user.getFirstName());
        user.setLastName(userCrudDto.getLastName() != null ? userCrudDto.getLastName() : user.getLastName());
        user.setUsername(userCrudDto.getUsername() != null ? userCrudDto.getUsername() : user.getUsername()); // todo if username exist it will throw an exception
        user.setAddress(userCrudDto.getAddress() != null ? userCrudDto.getFirstName() : user.getFirstName());
        user.setBirthday(userCrudDto.getBirthday() != null ? userCrudDto.getBirthday() : user.getBirthday());
//        user.setGender(userCrudDto.isGender() != null ? userCrudDto.isGender() : user.isGender());
        return user;
    }
}
