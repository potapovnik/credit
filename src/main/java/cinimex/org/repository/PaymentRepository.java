package cinimex.org.repository;

import cinimex.org.entity.BorrowerEntity;
import cinimex.org.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
    List<PaymentEntity> findAllByCreditId(Long id);
}
