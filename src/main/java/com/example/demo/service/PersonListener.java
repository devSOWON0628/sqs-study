package com.example.demo.service;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.example.demo.vo.Person;


@Service
public class PersonListener {
	private static final Logger LOGGER=LogManager.getLogger(PersonListener.class);

	@SqsListener(value="${sqs.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
	public void listen(@Payload Person person, @Headers Map<String, String> headers, Acknowledgment ack) {
		LOGGER.info("{}",person);
		LOGGER.info("{}",headers);
		LOGGER.info("{}",ack!=null);
		ack.acknowledge();	//메시지 delete
	}
}