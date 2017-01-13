package com.deviceregistry.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.deviceregistry.WebApiConstant;
import com.deviceregistry.domain.Device;
import com.deviceregistry.repository.DeviceRepository;

/**
 * The Class DeviceRegistryController.
 */
@RefreshScope
@RestController
@RequestMapping(WebApiConstant.RESOURCE_URL + "/device")
public class DeviceRegistryController {
	
	private static final Logger LOG = LoggerFactory.getLogger(DeviceRegistryController.class);

	/** The device repository. */
	@Autowired
	DeviceRepository deviceRepository;
	
	/** The rabbit template. */
	@Autowired
	private final RabbitTemplate rabbitTemplate;
	
	/** The service queue. */
	@Value("${syslog.rabbitmq.queue}")
	private String syslogServiceQueue;
	
	/** The rabbitmq enabled. */
	@Value("${rabbitmq.enabled}")
	private boolean rabbitmqEnabled;
	
	/**
	 * 
	 * Instantiates a new device registry controller.
	 *
	 * @param rabbitTemplate the rabbit template
	 * @param context the context
	 */
	public DeviceRegistryController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	/**
	 * Creates the.
	 *
	 * @param hardware the hardware
	 * @return the device
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	public Device create(@RequestBody Device hardware) {
		LOG.info(hardware.toString());
		
		if (rabbitmqEnabled) {
			this.rabbitTemplate.convertAndSend(syslogServiceQueue, hardware + " added!");
		}
		
		return this.deviceRepository.save(hardware);
	}

	/**
	 * Gets the.
	 *
	 * @param serial the serial
	 * @return the list
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	public List<Device> get(@RequestParam(name = "serial number", required = true) String serial) {
		LOG.info("Serial Number for get request: " + serial);
		return (List<Device>) this.deviceRepository.findBySerialNumber(serial);
	}
	
	/**
	 * Delete.
	 *
	 * @param serialNumber the serial number
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestParam(name = "serial number", required = true) String serial) {
		LOG.info("Serial Number for delete request: " + serial);
		List<Device> devices = (List<Device>) this.deviceRepository.findBySerialNumber(serial);
		
		if (!devices.isEmpty() && rabbitmqEnabled) {
			for (Device device : devices) {
				this.rabbitTemplate.convertAndSend(syslogServiceQueue, device + " deleted!");
			}
		}
		
		this.deviceRepository.deleteDevicesBySerialNumber(serial);
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	public List<Device> getAll() {
		return (List<Device>) this.deviceRepository.findAll();
	}
	
	/**
	 * Delete all.
	 */
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll() {
		this.deviceRepository.deleteAll();
	}
}
