package Team_15.MomsTicket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MomsTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomsTicketApplication.class, args);
	}

}
