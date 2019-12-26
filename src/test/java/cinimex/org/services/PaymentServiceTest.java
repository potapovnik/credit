package cinimex.org.services;

import cinimex.org.entity.CreditEntity;
import cinimex.org.mappers.PaymentMapper;
import cinimex.org.repository.CreditRepository;
import cinimex.org.repository.PaymentRepository;
import cinimex.org.transfer_obj.PaymentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(
        classes = {PaymentService.class},
        loader = AnnotationConfigContextLoader.class)
class PaymentServiceTest {
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private CreditRepository creditRepository;
    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private  PaymentService paymentService;



    @Test
    void payOffDebt() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(1L);
        paymentDto.setAmount(300f);
        paymentDto.setCreditId(1L);
        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setIsClosed(true);
        Optional<CreditEntity> creditEntity1 = Optional.of(creditEntity);
        Mockito.when(creditRepository.findById(any())).thenReturn(creditEntity1);
        System.out.println(paymentService.payOffDebt(paymentDto));
    }
}