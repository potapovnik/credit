package cinimex.org.services;


import cinimex.org.DTO.PaymentDto;
import cinimex.org.entity.CreditEntity;
import cinimex.org.entity.PaymentEntity;
import cinimex.org.exception.LogicException;
import cinimex.org.mappers.PaymentMapper;
import cinimex.org.repository.CreditRepository;
import cinimex.org.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("/payment")
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CreditRepository creditRepository;
    private final PaymentMapper paymentMapper;

    public boolean payOffDebt(PaymentDto paymentDto) {
        checkOnNull(paymentDto);
        List<PaymentEntity> payments = paymentRepository.findAllByCreditId(paymentDto.getCreditId());
        float sum = 0;
        for (PaymentEntity curPayment : payments) {
            sum += curPayment.getAmount();
        }
        Optional<CreditEntity> credit = creditRepository.findById(paymentDto.getCreditId());
        if (!credit.isPresent())
            throw new LogicException("Кредита для досрочного погашения не существует, id:" + paymentDto.getCreditId());
        if (sum + paymentDto.getAmount() < credit.get().getAmount())
            throw new LogicException("Данной суммы недостаточно для погашения кредита с id:" + paymentDto.getCreditId());
        else {
            paymentRepository.save(paymentMapper.fromDto(paymentDto));
            credit.get().setIsClosed(true);
            creditRepository.save(credit.get());
        }
        return true;
    }

    public boolean schedulePayment(PaymentDto paymentDto){
        checkOnNull(paymentDto);
        paymentRepository.save(paymentMapper.fromDto(paymentDto));
        return true;
    }

    private void checkOnNull(PaymentDto paymentDto) {
        boolean isNull = paymentDto.fieldsForCheck().stream().anyMatch(Objects::isNull);
        if (isNull)
            throw new NullPointerException("payment have one or more basic fields is null");
    }
}
