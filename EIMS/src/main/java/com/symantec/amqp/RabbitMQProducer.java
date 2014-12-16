package com.symantec.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RabbitMQProducer {

	private AnnotationConfigApplicationContext context;
	
	private RabbitTemplate template;
	
	public RabbitMQProducer(){
		context = new AnnotationConfigApplicationContext( RabbitConfiguration.class );
		template = context.getBean(RabbitTemplate.class);
	}
	
	public void sendARoutableMessage(){
		Message message = MessageBuilder.withBody("Test Message".getBytes()).build();
		template.send(RabbitConfiguration.EXCHANGNAME, RabbitConfiguration.ROUTEKEY, message, new CorrelationData("msg1"));
	}
	
	public void sendAUnroutableMessage(){
		Message message = MessageBuilder.withBody("Test Message- unroutable".getBytes()).build();
		template.send(RabbitConfiguration.EXCHANGNAME, "non-exist-route-key", message, new CorrelationData("msg1"));
	}
	
	public void closeContext(){
		if(null != context){
			context.close();
		}
	}
	
	public static void main(String[] args) {
		RabbitMQProducer producer = new RabbitMQProducer();
		try{
			producer.sendARoutableMessage();
			producer.sendAUnroutableMessage();
		}finally{
			producer.closeContext();
		}

	}

}
