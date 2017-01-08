package com.deviceregistry.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
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
@RestController
@RequestMapping(WebApiConstant.RESOURCE_URL + "/device")
public class DeviceRegistryController {

	/** The device repository. */
	@Autowired
	DeviceRepository deviceRepository;
	
	/** The rabbit template. */
	@Autowired
	private final RabbitTemplate rabbitTemplate;
	
	/** The context. */
	@Autowired
	private final ConfigurableApplicationContext context;
	
	/** The service queue. */
	@Value("${target.rabbitmq.queue}")
	private String serviceQueue;
	
	/**
	 * Instantiates a new device registry controller.
	 *
	 * @param rabbitTemplate the rabbit template
	 * @param context the context
	 */
	public DeviceRegistryController(RabbitTemplate rabbitTemplate, ConfigurableApplicationContext context) {
		this.rabbitTemplate = rabbitTemplate;
		this.context = context;
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
		System.out.println(hardware);
		this.rabbitTemplate.convertAndSend(serviceQueue, hardware.toString() + " connected.");
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
		System.out.println(serial);
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
	public void delete(@RequestParam String serialNumber) {
		this.deviceRepository.deleteDevicesBySerialNumber(serialNumber);
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
