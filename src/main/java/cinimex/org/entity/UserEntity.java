package cinimex.org.entity;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", schema = "public", catalog = "credit")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    private RoleEntity role;

    @OneToMany(mappedBy = "creditor")
    private List<CreditEntity> credits;


    public static final PasswordEncoder PASSWORD_ENCODER = new Pbkdf2PasswordEncoder("nikomuNeskazhu", 512, 512);

    public void setPassword(String password) {
        if (password == null)
            setPasswordWithoutEncode(password);
        else
            this.password = PASSWORD_ENCODER.encode(password);
    }

    public void setPasswordWithoutEncode(String password) {
        this.password = password;
    }


}
