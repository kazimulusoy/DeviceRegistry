package com.deviceregistry.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(WebApiConstant.RESOURCE_URL + "/device")
public class DeviceRegistryController {
	
	@Autowired
	DeviceRepository deviceRepository;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	public Device create(@RequestBody Device hardware) {
		System.out.println(hardware);
		return this.deviceRepository.save(hardware);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	public List<Device> get(@RequestParam(name = "serial number", required = true) String serial) {
		System.out.println(serial);
		return (List<Device>) this.deviceRepository.findBySerialNumber(serial);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Device hardware) {
		this.deviceRepository.delete(hardware);
	}

	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	public List<Device> getAll() {
		return (List<Device>) this.deviceRepository.findAll();
	}
	
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll() {
		this.deviceRepository.deleteAll();
	}
}
