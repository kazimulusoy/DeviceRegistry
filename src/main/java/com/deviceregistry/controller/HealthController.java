package com.deviceregistry.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deviceregistry.WebApiConstant;

@RestController
@RequestMapping(WebApiConstant.RESOURCE_URL)
public class HealthController {
	
	@RequestMapping(path = "/ping", method = RequestMethod.GET)
    public String custom() {
        return "pong " + new Date();
    }

}
