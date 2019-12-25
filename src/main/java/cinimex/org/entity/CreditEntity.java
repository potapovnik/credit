package cinimex.org.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "credit", schema = "public", catalog = "credit")
@Data
public class CreditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_seq")
    @SequenceGenerator(name = "credit_seq", sequenceName = "credit_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "annual_rate")
    private BigDecimal annualRate;

    @Column(name = "date_of_issue")
    private Timestamp dateOfIssue;

    @Column(name = "maturity_date")
    private Timestamp maturityDate;

    @Column(name = "closed")
    private Boolean isClosed;

    @ManyToOne
    private UserEntity creditor;

    @ManyToOne
    private BorrowerEntity borrower;

    @OneToMany(mappedBy = "credit")
    private List<PaymentEntity> payments;

    @OneToMany(mappedBy = "credit")
    private List<ScheduleEntity> schedules;
}
