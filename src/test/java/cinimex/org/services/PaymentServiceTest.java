package cinimex.org.services;

import cinimex.org.entity.CreditEntity;
import cinimex.org.entity.PaymentEntity;
import cinimex.org.exception.LogicException;
import cinimex.org.mappers.PaymentMapper;
import cinimex.org.repository.CreditRepository;
import cinimex.org.repository.PaymentRepository;
import cinimex.org.transfer_obj.PaymentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class PaymentServiceTest {
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private CreditRepository creditRepository;
    @Mock
    private PaymentMapper paymentMapper;
    @InjectMocks
    private PaymentService paymentService;


    @Test
    void payOffDebt() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(1L);
        paymentDto.setDate(new Timestamp(System.nanoTime()));
        paymentDto.setAmount(200f);
        paymentDto.setNumber(1);
        paymentDto.setCreditId(1L);
        Optional<CreditEntity> optionalCredit = Optional.empty();
        Mockito.when(creditRepository.findById(any())).thenReturn(optionalCredit);
        assertThatThrownBy(() -> paymentService.payOffDebt(paymentDto))
                .isInstanceOf(LogicException.class).hasMessageContaining("Кредита для досрочного погашения не существует, id:"+paymentDto.getCreditId());
        CreditEntity creditEntity = new CreditEntity();
        PaymentEntity payment= new PaymentEntity();
        payment.setAmount(30F);
        creditEntity.setAmount(300f);
        creditEntity.setId(1l);
        creditEntity.setIsClosed(true);
        creditEntity.setPayments(new ArrayList<PaymentEntity>(){{add(payment);}});
        optionalCredit = Optional.of(creditEntity);
        Mockito.when(creditRepository.findById(any())).thenReturn(optionalCredit);
        assertThatThrownBy(() -> paymentService.payOffDebt(paymentDto))
                .isInstanceOf(LogicException.class).hasMessageContaining("Данной суммы недостаточно для погашения кредита с id:"+paymentDto.getCreditId());
        payment.setAmount(100f);
        assertTrue(paymentService.payOffDebt(paymentDto));
    }
}