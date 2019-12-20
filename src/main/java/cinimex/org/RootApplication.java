package cinimex.org;

import cinimex.org.repository.BorrowerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RootApplication {
    private static BorrowerRepository borrowerRepository;

    public RootApplication(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class);
    }

}
