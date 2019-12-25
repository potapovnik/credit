package cinimex.org.mappers;

import cinimex.org.transfer_obj.BorrowerDto;
import cinimex.org.entity.BorrowerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowerMapper {

    BorrowerEntity fromDto(BorrowerDto borrowerDto);

    BorrowerDto toDto(BorrowerEntity borrowerEntity);

    List<BorrowerEntity> fromDto(List<BorrowerDto> borrowerDtoList);

    List<BorrowerDto> toDto(List<BorrowerEntity> borrowerEntityList);
}