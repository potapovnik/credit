package cinimex.org.mappers;

import cinimex.org.transfer_obj.CreditDto;
import cinimex.org.entity.CreditEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    CreditEntity fromDto(CreditDto creditDto);

    CreditDto toDto(CreditEntity creditEntity);

    List<CreditEntity> fromDto(List<CreditDto> creditDtoList);

    List<CreditDto> toDto(List<CreditEntity> creditEntityList);
}