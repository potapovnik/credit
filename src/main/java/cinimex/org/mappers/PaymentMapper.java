package cinimex.org.mappers;

import cinimex.org.transfer_obj.PaymentDto;
import cinimex.org.entity.PaymentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentEntity fromDto(PaymentDto paymentDto);

    PaymentDto toDto(PaymentEntity paymentEntity);

    List<PaymentEntity> fromDto(List<PaymentDto> paymentDtoList);

    List<PaymentDto> toDto(List<PaymentEntity> paymentEntityList);
}