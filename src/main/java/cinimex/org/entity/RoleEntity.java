package cinimex.org.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "public", catalog = "credit")
@Data
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
