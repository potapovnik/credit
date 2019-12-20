package cinimex.org.mappers;

import cinimex.org.DTO.ScheduleDto;
import cinimex.org.DTO.UserDto;
import cinimex.org.entity.ScheduleEntity;
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