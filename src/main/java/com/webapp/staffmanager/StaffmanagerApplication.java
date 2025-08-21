package com.webapp.staffmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StaffmanagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(StaffmanagerApplication.class, args);
		;

		// --- config to enable startup endpoint of actuator
		// SpringApplication app = new SpringApplication(StaffmanagerApplication.class);
		// app.setApplicationStartup(new BufferingApplicationStartup(2048));
		// app.run(args);

	}

}
