package com.symantec.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.ReceiveAndReplyCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RabbitMQConsumer {

	public void consumeMessage(){
		AnnotationConfigApplicationContext context = null;
		
		try{
			context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
			RabbitTemplate template = context.getBean(RabbitTemplate.class);
			//Message msg = (Message) template.receive(RabbitConfiguration.QUEUENAME);
			
			//receive message from one queue and forward the message to another queue
			boolean received = template.receiveAndReply(RabbitConfiguration.QUEUENAME, 
					new ReceiveAndReplyCallback<Object, Message>(){

						@Override
						public Message handle(Object msg) {
							System.out.println("----Received Message---" + msg);
							return MessageBuilder.withBody("Received".getBytes()).build();
						}
				
			}, RabbitConfiguration.FORWARDEXCHANGE, RabbitConfiguration.FORWARDROUTINGKEY);
			
			if(received){
			}
		}finally{
			context.close();
		}
	}
	
	public static void main(String[] args) {
		RabbitMQConsumer consumer = new RabbitMQConsumer();
		consumer.consumeMessage();
	}

}
