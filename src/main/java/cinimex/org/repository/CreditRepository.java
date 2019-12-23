package cinimex.org.repository;

import cinimex.org.entity.BorrowerEntity;
import cinimex.org.entity.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CreditRepository extends JpaRepository<CreditEntity,Long> {
    List<CreditEntity> findAllById(Long id);
    List<CreditEntity> findByDateOfIssueBetween(Date fromDate,Date toDate);
    List<CreditEntity> findAllByIsClosed(boolean isClosed);
}
