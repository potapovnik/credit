package cinimex.org.transfer_obj;

import lombok.Data;

@Data
public class UserDto {


    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String lastName;


}
