package cinimex.org.entity;

import lombok.Data;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role", schema = "public", catalog = "credit")
@Data
public class RoleEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;


}
