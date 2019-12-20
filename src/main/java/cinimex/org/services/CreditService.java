package cinimex.org.services;

import cinimex.org.DTO.CreditDto;
import cinimex.org.DTO.ScheduleDto;
import cinimex.org.entity.BorrowerEntity;
import cinimex.org.entity.CreditEntity;
import cinimex.org.entity.PaymentEntity;
import cinimex.org.entity.ScheduleEntity;
import cinimex.org.exception.LogicException;
import cinimex.org.mappers.CreditMapper;
import cinimex.org.repository.BorrowerRepository;
import cinimex.org.repository.CreditRepository;
import cinimex.org.repository.PaymentRepository;
import cinimex.org.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.parser.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("credit")
@AllArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;
    private final BorrowerRepository borrowerRepository;
    private final PaymentRepository paymentRepository;
    private final ScheduleRepository scheduleRepository;
    private final CreditMapper creditMapper;

    public boolean create(CreditDto creditDto) {
        checkOnNull(creditDto);
        if (creditDto.getAmount() <= 0) {
            throw new LogicException("amount of credit cant be equals or less then 0");
        }
        if (creditDto.getIsClosed() == null) // если не выставлено, то по умолчанию "не закрыт"
            creditDto.setIsClosed(false);
        CreditEntity saved = creditMapper.fromDto(creditDto);
        creditMapper.toDto(saved);
        return true;
    }

    public boolean close(Long id) {
        Optional<CreditEntity> credit = creditRepository.findById(id);
        if (credit.isPresent()) {
            // logger "Кредит для закрытия с id: " + creditDto.getId() + " не существует"
            return false;
        } else {
            credit.get().setIsClosed(true);
            creditRepository.save(credit.get());
            return true;
        }
    }

    public List<CreditDto> findAll() {
        return creditMapper.toDto(creditRepository.findAll());
    }

    public List<CreditDto> findAllByFIO(String surname, String name, String lastname) {
        BorrowerEntity borrower = borrowerRepository.findAllByNameAndSurnameAndLastName(name, surname, lastname);
        List<CreditEntity> creditOfBorrower = creditRepository.findAllById(borrower.getId());
        return creditMapper.toDto(creditOfBorrower);
    }

    public List<CreditDto> findForInterval(Date fromDate, Date toDate) {
        if (fromDate.compareTo(toDate) <= 0)// начало интервала должно быть больше чем конец
        {
        } // logger
        List<CreditEntity> allCreditInInterval = creditRepository.findByStartDateOfIssueBetween(fromDate, toDate);
        return creditMapper.toDto(allCreditInInterval);
    }

    public List<CreditDto> findDangerous() {
        List<CreditEntity> allNotClosedCredit = creditRepository.findAllByIsClosed(false); // ищем все не закрытые
        List<CreditEntity> dangerousCredits = new ArrayList<>();
        for (CreditEntity cur : allNotClosedCredit) {
            List<PaymentEntity> payments = paymentRepository.findAllByCreditId(cur.getId());
            List<ScheduleEntity> schedule = scheduleRepository.findAllByCreditId(cur.getId());
           //  for (int i)

        }
    }

    private void checkOnNull(CreditDto creditDto) {

        boolean isNull = creditDto.getFieldsForCheck().stream().anyMatch(Objects::isNull);
        if (isNull)
            throw new NullPointerException("credit have one or more basic fields is null");
    }


}
