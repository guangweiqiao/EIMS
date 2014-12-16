package com.symantec.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RabbitMQConsumer {

	public void consumeMessage(){
		AnnotationConfigApplicationContext context = null;
		
		try{
			context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
			RabbitTemplate template = context.getBean(RabbitTemplate.class);
			Message msg = (Message) template.receive(
					RabbitConfiguration.QUEUENAME);
			System.out.println("===============" + msg);
		}finally{
			context.close();
		}
	}
	
	public static void main(String[] args) {
		RabbitMQConsumer consumer = new RabbitMQConsumer();
		consumer.consumeMessage();
	}

}
