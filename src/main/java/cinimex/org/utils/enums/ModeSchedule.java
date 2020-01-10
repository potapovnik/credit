package cinimex.org.utils.enums;

import cinimex.org.exception.LogicException;
import io.swagger.models.auth.In;

import java.util.Arrays;

public enum ModeSchedule {
    HALF_YEAR(1, 6),
    YEAR(2, 12),
    TWO_YEAR(3, 24),
    FIVE_YEAR(4, 80),
    FREE(5,null);

    private final Integer id;
    private final Integer months;

    ModeSchedule(Integer id, Integer months) {
        this.id = id;
        this.months = months;
    }

    public Integer getId() {
        return id;
    }

    public  Integer getMonths() {
        return months;
    }

    public static ModeSchedule getMonthsById(Integer id) {
        return Arrays.stream(ModeSchedule.values()).
                findAny()
                .filter(a -> a.getId().equals(id))
                .orElseThrow(IllegalStateException::new);
    }
}
