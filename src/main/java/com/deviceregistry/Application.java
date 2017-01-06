package com.deviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.deviceregistry.conf.RabbitMqConfiguration;
import com.deviceregistry.conf.SwaggerConfiguration;

@Import({ SwaggerConfiguration.class, RabbitMqConfiguration.class })
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
