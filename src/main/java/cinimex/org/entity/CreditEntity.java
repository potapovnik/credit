package cinimex.org.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
    private Integer amount;

    @Column(name = "annual_rate")
    private BigDecimal annualRate;

    @Column(name = "date_of_issue")
    private Timestamp dateOfIssue;

    @Column(name = "closed")
    private Boolean isClosed;

    @Column(name = "creditor_id")
    private Long creditorId;

    @Column(name = "borrower_id")
    private Long borrowerId;
}
