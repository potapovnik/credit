package cinimex.org;

import cinimex.org.controllers.BorrowerController;
import cinimex.org.mappers.BorrowerMapper;
import cinimex.org.services.BorrowerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {BorrowerController.class, BorrowerService.class, BorrowerMapper.class})
public class RootApplication {
    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }

}
