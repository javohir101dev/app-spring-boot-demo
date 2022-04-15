package demo.uz.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCrudDto {

    private String firstName;

    private String lastName;

    private String username;

    private String address;

    private boolean gender;

    private String birthday;

    @ApiModelProperty(hidden = true)
    private boolean active = true;
}
