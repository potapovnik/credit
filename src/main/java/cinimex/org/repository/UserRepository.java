package cinimex.org.repository;

import cinimex.org.entity.BorrowerEntity;
import cinimex.org.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
