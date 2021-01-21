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

import com.example.demo.vo.Person;

import lombok.extern.slf4j.Slf4j;


@Component
public class PersonListener {
	private static final Logger LOGGER=LogManager.getLogger(SqsMessageSender.class);

	@SqsListener(value="sqs-study-sowon", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
	public void listen(@Payload Person person, @Headers Map<String, String> headers, Acknowledgment ack) {
		LOGGER.info("{}",person);
		LOGGER.info("{}",headers);
		LOGGER.info("{}",ack!=null);
	}
}
