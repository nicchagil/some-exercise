package com.nicchagil.util.heartbeat.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heartbeat")
public class HeartbeatController {
	
	@GetMapping() // http://127.0.0.1/heartbeat
	public String heartbeat() {
		return "OK";
	}

}
