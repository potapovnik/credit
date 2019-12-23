package cinimex.org.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Data
public class CreditDto {

    private Long id;
    private Float amount;
    private BigDecimal annualRate;
    private Timestamp dateOfIssue;
    private Timestamp maturityDate;
    private Boolean isClosed;
    private Integer creditorId;
    private Integer borrowerId;

    public List<String> fieldsForCheck(){
        return Arrays.asList(amount.toString(),annualRate.toString(),dateOfIssue.toString(),maturityDate.toString(),creditorId.toString(),borrowerId.toString());
    }

}
