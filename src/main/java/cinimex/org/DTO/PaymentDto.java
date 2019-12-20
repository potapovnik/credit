package cinimex.org.DTO;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
public class PaymentDto {
    private Long id;
    private Integer amount;
    private Timestamp date;
    private Long creditId;


}
