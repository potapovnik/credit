package cinimex.org.mappers;

import cinimex.org.transfer_obj.UserDto;
import cinimex.org.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity fromDto(UserDto userDto);

    UserDto toDto(UserEntity userEntity);

    List<UserEntity> fromDto(List<UserDto> userDtoList);

    List<UserDto> toDto(List<UserEntity> userEntityList);
}