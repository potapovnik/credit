package cinimex.org.DTO;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
public class ScheduleDto {

    private Long id;
    private Float amount;
    private Integer number;
    private Timestamp date;
    private Long creditId;


}
