package cinimex.org.repository;

import cinimex.org.entity.BorrowerEntity;
import cinimex.org.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {
}
