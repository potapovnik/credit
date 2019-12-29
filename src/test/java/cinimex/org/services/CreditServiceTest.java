package cinimex.org.services;

import cinimex.org.entity.CreditEntity;
import cinimex.org.entity.PaymentEntity;
import cinimex.org.entity.ScheduleEntity;
import cinimex.org.exception.LogicException;
import cinimex.org.mappers.CreditMapper;
import cinimex.org.mappers.config.ConfigurationTest;
import cinimex.org.repository.BorrowerRepository;
import cinimex.org.repository.CreditRepository;
import cinimex.org.repository.ScheduleRepository;
import cinimex.org.transfer_obj.CreditDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(
        classes = {ConfigurationTest.class},
        loader = AnnotationConfigContextLoader.class)
class CreditServiceTest {
    @InjectMocks
    private CreditService creditService;
    @Spy
    private CreditRepository creditRepository;
    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private BorrowerRepository borrowerRepository;
    @Autowired
    private CreditMapper creditMap;
    @Mock
    private CreditMapper creditMapper;

    @Test
    void create() {
        CreditDto creditDto = new CreditDto();
        creditDto.setAmount(-1f);
        creditDto.setAnnualRate(new BigDecimal(5));
        creditDto.setDateOfIssue(new Timestamp(System.nanoTime()));
        creditDto.setMaturityDate(new Timestamp(System.nanoTime() + 10000));
        creditDto.setBorrowerId(1);
        creditDto.setCreditorId(1);
        assertThatThrownBy(() -> creditService.create(creditDto))
                .isInstanceOf(LogicException.class)
                .hasMessageContaining("Величина денег должна быть больше 0");
        creditDto.setAmount(200f);
        CreditEntity creditEntity = creditMap.fromDto(creditDto);
        Mockito.when(creditRepository.save(creditMapper.fromDto(creditDto))).thenReturn(creditEntity);
        assertTrue(creditService.create(creditDto));

    }

    @Test
    void close() {
        assertThatThrownBy(() -> creditService.close(1L))
                .isInstanceOf(LogicException.class)
                .hasMessageContaining("Кредит для закрытия с id: " + 1 + " не существует");

        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setId(1L);
        Optional<CreditEntity> optionalCredit = Optional.of(creditEntity);
        Mockito.when(creditRepository.findById(1L)).thenReturn(optionalCredit);
        assertTrue(creditService.close(1L));

    }

    @Test
    void findForInterval() {
        Date fromDate = new Date();
        Date toDate = new Date();
        fromDate.setTime(toDate.getTime() + 1);
        assertThatThrownBy(() -> creditService.findForInterval(fromDate, toDate))
                .isInstanceOf(LogicException.class)
                .hasMessageContaining("fromDate должно быть меньше чем toDate");
    }

    @Test
    void findDangerous() {
        PaymentEntity payment = new PaymentEntity();
        payment.setAmount(300f);
        payment.setNumber(1);
        payment.setDate(new Timestamp(System.currentTimeMillis()));
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setAmount(400f);
        schedule.setNumber(1);
        schedule.setDate(new Timestamp(System.currentTimeMillis() + 100_000));
        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setPayments(new ArrayList<PaymentEntity>() {{
            add(payment);
        }});
        creditEntity.setSchedules(new ArrayList<ScheduleEntity>() {{
            add(schedule);
        }});
        Mockito.when(creditRepository.findAllByIsClosed(false)).thenReturn(new ArrayList<CreditEntity>() {{
            add(creditEntity);
        }});
        assertNotEquals(creditService.findDangerous(), null);
        payment.setAmount(400f);
        payment.setDate(new Timestamp(System.currentTimeMillis() + 100_001));
        assertNotEquals(creditService.findDangerous(), null);
        payment.setDate(new Timestamp(System.currentTimeMillis()));
        assertEquals(creditService.findDangerous(), null);
    }

}