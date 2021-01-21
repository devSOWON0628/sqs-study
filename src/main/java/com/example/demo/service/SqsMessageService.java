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
public class SqsMessageService {
	private final QueueMessagingTemplate queueMessagingTemplate;
	
	private static final Logger LOGGER=LogManager.getLogger(SqsMessageService.class);
	
	//AmazonSqsAsync 와 AmazonSqs의 차이..는 모르겟지만 성능상으로는 아래 코드에 둘다 사용해도 됨
	// Async붙은건 비동기 방식으로 실행되나 그럼 그냥은 동기방식?
	// AmazonSqsAsync는 AmazonSqs를 상속받음 AmazonSqs의 기능을 전부 사용가능하니까 구냥 AmazonSqsAsync를 쓰쟈 
	@Autowired
	public SqsMessageService(AmazonSQSAsync amazonSqsAsync) {
		//AmazonSQSAsync빈을 이용해 메시지 템플릿을 만드는듯
		this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSqsAsync);
	}
	
//	public void sendMessage(String message) {
//		Message<String> newMessage = MessageBuilder.withPayload(message).build();
//		queueMessagingTemplate.send("sqs-study-sowon", newMessage);
//	}
	
	public void sendMessage(Person person) {
		queueMessagingTemplate.convertAndSend("sqs-study-sowon",person);
		LOGGER.info("SQS에 메시지 전송 : "+person);
	}	

}
