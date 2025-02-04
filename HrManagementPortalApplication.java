package com.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HrManagementPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrManagementPortalApplication.class, args);
		System.out.println("Project Running");
	}

}
