package cinimex.org.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "schedule", schema = "public", catalog = "credit")
@Data
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_seq")
    @SequenceGenerator(name = "schedule_seq", sequenceName = "schedule_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Float amount;


    @Column(name = "number", insertable = false, columnDefinition = "serial", updatable = false)
    private Integer number;

    @Column(name = "date")
    private Timestamp date;

    @ManyToOne
    private CreditEntity credit;


}
