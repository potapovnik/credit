package cinimex.org.DTO;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Data
public class PaymentDto {
    private Long id;
    private Float amount;
    private Integer number;
    private Timestamp date;
    private Long creditId;

    public List<String> fieldsForCheck(){
        return Arrays.asList(amount.toString(),number.toString(),date.toString(),creditId.toString());
    }

}
