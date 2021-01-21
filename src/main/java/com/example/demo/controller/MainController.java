package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SqsMessageSender;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MainController {

	private final SqsMessageSender messageSender;
	
	@PostMapping("/message")
	public void sendMessage(@RequestBody String message) {
		messageSender.sendMessage(message);
	}
	
	@GetMapping("/message")
	public void getMessage() {
		messageSender.getMessage();
	}
	
}
