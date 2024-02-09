package com.eventorganizator.Event.Organizator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EventOrganizatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventOrganizatorApplication.class, args);
	}

}
