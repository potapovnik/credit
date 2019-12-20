package cinimex.org.services;

import cinimex.org.DTO.BorrowerDto;
import cinimex.org.entity.BorrowerEntity;
import cinimex.org.mappers.BorrowerMapper;
import cinimex.org.repository.BorrowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("borrower")
@AllArgsConstructor
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;
    private final BorrowerMapper borrowerMapper;

    public Long create(BorrowerDto borrowerDto) {
        checkOnNull(borrowerDto);
        BorrowerEntity borrowerEntity = borrowerMapper.fromDto(borrowerDto);
        BorrowerEntity saved = borrowerRepository.save(borrowerEntity);
        return saved.getId();
    }

    private void checkOnNull(BorrowerDto borrowerDto) {
        boolean isNull = borrowerDto.getFieldsForCheck().stream().anyMatch(o -> o == null);
        if (isNull)
            throw new NullPointerException("borrower have one or more basic fields is null");
    }
}

