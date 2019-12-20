package cinimex.org.DTO;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Data
public class CreditDto {

    private Long id;
    private Integer amount;
    private BigDecimal annualRate;
    private Timestamp dateOfIssue;
    private Boolean isClosed;

    public List<String> getFieldsForCheck(){
        return Arrays.asList(amount.toString(),annualRate.toString(),dateOfIssue.toString());
    }

}
