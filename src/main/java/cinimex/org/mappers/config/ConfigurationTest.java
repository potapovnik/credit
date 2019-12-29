package cinimex.org.mappers.config;


import cinimex.org.mappers.CreditMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class ConfigurationTest {
    @Bean
    public CreditMapper creditMapper(){
        return Mappers.getMapper(CreditMapper.class);
    }
}
