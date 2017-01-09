package com.deviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.deviceregistry.conf.RabbitMqConfiguration;
import com.deviceregistry.conf.SwaggerConfiguration;

/**
 * The Class Application.
 */
@Import({ SwaggerConfiguration.class, RabbitMqConfiguration.class })
@SpringBootApplication
public class DeviceRegistryApplication {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(DeviceRegistryApplication.class, args);
	}
}
