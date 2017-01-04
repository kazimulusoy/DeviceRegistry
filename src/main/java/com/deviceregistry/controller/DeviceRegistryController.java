package com.deviceregistry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.deviceregistry.WebApiConstant;

@RestController
@RequestMapping(WebApiConstant.RESOURCE_URL)
public class DeviceRegistryController {
	
	@RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String custom() {
        return "hello";
    }
}
