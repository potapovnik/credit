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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
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
        CreditEntity saved = creditRepository.save(creditMapper.fromDto(creditDto)); //Сохраняем кредит
        Calendar fromDate = new GregorianCalendar();
        fromDate.setTimeInMillis(creditDto.getDateOfIssue().getTime());
        Calendar toDate = new GregorianCalendar();
        toDate.setTimeInMillis(creditDto.getMaturityDate().getTime());
        int countSchedule = (toDate.get(Calendar.YEAR) - fromDate.get(Calendar.YEAR)) * 12 + (toDate.get(Calendar.MONTH) - fromDate.get(Calendar.MONTH));// количество платежей
        for (int i = 0; i < countSchedule; i++) {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setCreditId(saved.getId());
            scheduleEntity.setAmount(creditDto.getAmount() / countSchedule);
            GregorianCalendar lGregorianCalendar = new GregorianCalendar();
            lGregorianCalendar.setTimeInMillis(creditDto.getDateOfIssue().getTime());
            lGregorianCalendar.add(Calendar.MONTH, i + 1);
            Timestamp dateOffPayment = new Timestamp(lGregorianCalendar.getTimeInMillis());
            scheduleEntity.setDate(dateOffPayment);
            scheduleEntity.setNumber(null);
            scheduleRepository.save(scheduleEntity);
        }
        creditMapper.toDto(saved);
        return true;
    }

    public boolean close(Long id) {
        Optional<CreditEntity> credit = creditRepository.findById(id);
        if (!credit.isPresent()) {
            throw new LogicException("Кредит для закрытия с id: " + id + " не существует");
        } else {
            credit.get().setIsClosed(true);
            creditRepository.save(credit.get());
            return true;
        }
    }

    public List<CreditDto> findAll() {
        return creditMapper.toDto(creditRepository.findAll());
    }

    public List<CreditDto> findAllByFIO(String name, String surname, String lastname) {

        BorrowerEntity borrower = borrowerRepository.findAllByNameAndSurnameAndLastName(name, surname, lastname);
        List<CreditEntity> creditOfBorrower = creditRepository.findAllById(borrower.getId());
        return creditMapper.toDto(creditOfBorrower);
    }

    public List<CreditDto> findForInterval(Date fromDate, Date toDate) {
        if (fromDate.compareTo(toDate) <= 0)// начало интервала должно быть больше чем конец
        {
        } // logger
        List<CreditEntity> allCreditInInterval = creditRepository.findByDateOfIssueBetween(fromDate, toDate);
        return creditMapper.toDto(allCreditInInterval);
    }

    public List<CreditDto> findDangerous() {
        List<CreditEntity> allNotClosedCredit = creditRepository.findAllByIsClosed(false); // ищем все не закрытые
        List<CreditEntity> dangerousCredits = new ArrayList<>();
        for (CreditEntity cur : allNotClosedCredit) {
            List<PaymentEntity> payments = paymentRepository.findAllByCreditId(cur.getId());
            List<ScheduleEntity> schedule = scheduleRepository.findAllByCreditId(cur.getId());
            boolean expired = false;
            for (ScheduleEntity curSchedule : schedule) { // сверим все графики с реально внесёнными платежами
                float sum = 0;
                for (PaymentEntity curPayment : payments) {
                    if (curPayment.getNumber().equals(curSchedule.getNumber())) { // платёжки принадлежащие данному графику
                        if (curPayment.getDate().compareTo(curSchedule.getDate()) >= 1) { // если просрочено, то уже опасный
                            expired = true;
                            break;
                        }
                        sum += curPayment.getAmount();
                    }
                }
                if (sum < curSchedule.getAmount())
                    expired = true;
            }
            if (expired)
                dangerousCredits.add(cur);
        }
        return creditMapper.toDto(dangerousCredits);
    }

    public CreditDto findById(Long id) {
        Optional<CreditEntity> credit = creditRepository.findById(id);
        if (!credit.isPresent())
            throw new LogicException("Кредита с id:" + id + " не существует");
        return creditMapper.toDto(credit.get());
    }

    private void checkOnNull(CreditDto creditDto) {

        boolean isNull = creditDto.fieldsForCheck().stream().anyMatch(Objects::isNull);
        if (isNull)
            throw new NullPointerException("credit have one or more basic fields is null");
    }


}
