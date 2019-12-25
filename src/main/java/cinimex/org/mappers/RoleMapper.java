package cinimex.org.mappers;

import cinimex.org.transfer_obj.RoleDto;
import cinimex.org.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleEntity fromDto(RoleDto roleDto);

    RoleDto toDto(RoleEntity roleEntity);

    List<RoleEntity> fromDto(List<RoleDto> roleDtoList);

    List<RoleDto> toDto(List<RoleEntity> roleEntityList);
}