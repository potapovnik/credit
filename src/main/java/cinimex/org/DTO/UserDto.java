package cinimex.org.DTO;

import lombok.Data;

import javax.persistence.*;

@Data
public class UserDto {


    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String lastName;


}
