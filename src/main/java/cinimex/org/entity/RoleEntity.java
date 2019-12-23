package cinimex.org.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "public", catalog = "credit")
@Data
public class RoleEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
