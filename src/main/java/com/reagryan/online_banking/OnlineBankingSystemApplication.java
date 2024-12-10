package com.reagryan.online_banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OnlineBankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingSystemApplication.class, args);
	}

}
