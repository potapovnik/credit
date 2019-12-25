package cinimex.org.transfer_obj;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ScheduleDto {

    private Long id;
    private Float amount;
    private Integer number;
    private Timestamp date;
    private Long creditId;


}
