package pl.corp.dziadzia.MnemoTutorBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pl.corp.dziadzia.MnemoTutorBack.repo")
public class MnemoTutorBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MnemoTutorBackApplication.class, args);
	}
}
