package cinimex.org.mappers;

import cinimex.org.transfer_obj.ScheduleDto;
import cinimex.org.entity.ScheduleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleEntity fromDto(ScheduleDto scheduleDto);

    ScheduleDto toDto(ScheduleEntity scheduleEntity);

    List<ScheduleEntity> fromDto(List<ScheduleDto> scheduleDtoList);

    List<ScheduleDto> toDto(List<ScheduleEntity> scheduleEntityList);
}