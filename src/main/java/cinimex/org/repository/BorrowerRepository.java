package cinimex.org.repository;

import cinimex.org.entity.BorrowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<BorrowerEntity,Long> {
}
