package pl.fekeni.PekeN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PekeNApplication {

	public static void main(String[] args) {
		SpringApplication.run(PekeNApplication.class, args);
	}

}
