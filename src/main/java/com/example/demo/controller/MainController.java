package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PersonListener;
import com.example.demo.service.SqsMessageService;
import com.example.demo.vo.Person;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MainController {
	
	@Autowired
	PersonListener personListener;
	private final SqsMessageService messageService;
	
//	@PostMapping("/message")
//	public void sendMessage(@RequestBody Person person) {
//		messageService.sendMessage(person);
//	}
	@PostMapping("/message")
	public void sendMessage(@RequestBody Person person) {
		messageService.sendMessage(person);
	}

}