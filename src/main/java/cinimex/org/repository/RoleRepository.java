package cinimex.org.repository;

import cinimex.org.entity.BorrowerEntity;
import cinimex.org.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
