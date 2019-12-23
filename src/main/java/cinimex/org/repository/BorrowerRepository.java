package cinimex.org.repository;

import cinimex.org.entity.BorrowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowerRepository extends JpaRepository<BorrowerEntity, Long> {
    BorrowerEntity findAllByNameAndSurnameAndLastName(String name, String surname, String lastName);
}
