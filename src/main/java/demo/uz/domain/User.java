package demo.uz.domain;

import demo.uz.model.UserCrudDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public static User toUser(User user, UserCrudDto userCrudDto) {
        user.setFirstName(userCrudDto.getFirstName() != null ? userCrudDto.getFirstName() : user.getFirstName());
        user.setLastName(userCrudDto.getLastName() != null ? userCrudDto.getLastName() : user.getLastName());
        user.setUsername(userCrudDto.getUsername() != null ? userCrudDto.getUsername() : user.getUsername()); // todo if username exist it will throw an exception
        user.setAddress(userCrudDto.getAddress() != null ? userCrudDto.getFirstName() : user.getFirstName());

        user.setBirthday(userCrudDto.getBirthday() != null ? LocalDate.parse(userCrudDto.getBirthday(),DateTimeFormatter.ofPattern("yyyy-MM-dd")) : user.getBirthday());
//        user.setGender(userCrudDto.isGender() != null ? userCrudDto.isGender() : user.isGender());
        return user;
    }
}
