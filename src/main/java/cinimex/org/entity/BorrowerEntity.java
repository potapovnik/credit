package cinimex.org.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "borrower", schema = "public", catalog = "credit")
@Data
public class BorrowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrower_seq")
    @SequenceGenerator(name = "borrower_seq", sequenceName = "borrower_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "passport_number")
    private Integer passportNumber;

    @Column(name = "passport_series")
    private Integer passportSeries;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "borrower")
    private List<CreditEntity> credits;

}
