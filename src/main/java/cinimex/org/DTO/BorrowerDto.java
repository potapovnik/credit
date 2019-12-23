package cinimex.org.DTO;

import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Data
public class BorrowerDto {

    private Long id;
    private String name;
    private String surname;
    private String lastName;
    private Integer passportNumber;
    private Integer passportSeries;
    private String address;
    private String phoneNumber;
    private Long creditorId;
    private Long borrowerId;

    public List<String> fieldsForCheck() {
        return Arrays.asList(name, surname,
                passportNumber.toString(),
                passportSeries.toString(),
                creditorId.toString(),
                borrowerId.toString());
    }

}
