package cinimex.org.repository;

import cinimex.org.entity.BorrowerEntity;
import cinimex.org.entity.PaymentEntity;
import cinimex.org.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {
    List<ScheduleEntity> findAllByCreditId(Long id);
}
