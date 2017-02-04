package com.deviceregistry.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class HealthController.
 */
@RefreshScope
@RestController
public class HealthController {
	
	/** The service exchange. */
	@Value("${syslog.rabbitmq.exchange}")
	private String syslogServiceExchange;
	
	/** The rabbitmq enabled. */
	@Value("${rabbitmq.enabled}")
	private boolean rabbitmqEnabled;
	
	/**
	 * Custom.
	 *
	 * @return the string
	 */
	@RequestMapping(path = "/hello", method = RequestMethod.GET)
	@Transactional
    public String custom() {
        return "hello";
    }

	/**
	 * Gets conf from config server.
	 *
	 * @return the all
	 */
	@RequestMapping(value = "/conf", method = RequestMethod.GET)
	@Transactional
	public String getConf() {
		return "syslog exchange name: " + this.syslogServiceExchange + " - " + "rabbit messaging enabled: " + this.rabbitmqEnabled;
	}
}
