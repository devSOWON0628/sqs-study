package com.example.demo.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.example.demo.controller.MainController;
import com.example.demo.vo.Person;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
public class SqsMessageSender {
	private final QueueMessagingTemplate queueMessagingTemplate;
	
	private static final Logger LOGGER=LogManager.getLogger(SqsMessageSender.class);
	
	@Autowired
	public SqsMessageSender(AmazonSQS amazonSqs) {
		this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync)amazonSqs);
	}
//	public void sendMessage(String message) {
//		Message<String> newMessage = MessageBuilder.withPayload(message).build();
//		queueMessagingTemplate.send("sqs-study-sowon", newMessage);
//	}
	
	public void sendMessage(Person person) {
		queueMessagingTemplate.convertAndSend("sqs-study-sowon",person);
//		queueMessagingTemplate.convertAndSend("sqs-study-sowon",new Person(name,20));

		LOGGER.info("SQS에 메시지 전송 : "+person);
	}	

	public void getMessage() {
		Person person = queueMessagingTemplate.receiveAndConvert("sqs-study-sowon",Person.class);//("sqs-study-sowon", Person.class);
		System.out.println("SQS에서 받은 값 "+ person.toString());
		
	}
	

	
}
