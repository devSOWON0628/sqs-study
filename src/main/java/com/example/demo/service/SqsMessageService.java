package com.example.demo.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
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
public class SqsMessageService {
	private final QueueMessagingTemplate queueMessagingTemplate;
	
	private static final Logger LOGGER=LogManager.getLogger(SqsMessageService.class);

	@Autowired
	public SqsMessageService(AmazonSQSAsync  amazonSqsAsync) {
		//AmazonSQSAsync빈을 이용해 메시지 템플릿을 만드는듯
		this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSqsAsync);
		 SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
    	 factory.setAmazonSqs(amazonSqsAsync);
		 factory.setWaitTimeOut(1); // less than 10 sec
	}

	public void sendMessage(Person person) {
		queueMessagingTemplate.convertAndSend("sqs-study-sowon",person);
		LOGGER.info("SQS에 메시지 전송 : "+person);
	}	

}
