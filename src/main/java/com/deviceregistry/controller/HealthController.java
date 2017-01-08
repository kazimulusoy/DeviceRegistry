package com.deviceregistry.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deviceregistry.WebApiConstant;

@RestController
@RequestMapping(WebApiConstant.RESOURCE_URL)
public class HealthController {
	
	@Value(("${service.name}"))
	private String serviceName;
	
	@RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String custom() {
        return this.serviceName + " is running.";
    }

}
