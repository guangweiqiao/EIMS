package com.symantec.amqp;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
	
	private static final String HOSTNAME     = "localhost";
	public static final String QUEUENAME     = "leaveMessageQueue";
	public static final String EXCHANGNAME   = "leaveMessageExchange";
	public static final String ROUTEKEY      = "leave.msg";
	public static final int CHANNELCACHESIZE = 25; //default value is 1;
	public static final String USERNAME      = "guest";
	public static final String PASSWORD      = "guest";
	
	//for forward
	public static final String FORWARDEXCHANGE   = "forwardExchange";
	public static final String FORWARDQUEUE      = "forwardQueue";
	public static final String FORWARDROUTINGKEY = "forwarKey";
	
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(HOSTNAME);
        connectionFactory.setUsername(USERNAME); //optional, default value is "guest".
        connectionFactory.setPassword(PASSWORD); //optional, default value is "guest".
        connectionFactory.setChannelCacheSize(CHANNELCACHESIZE); 
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        
        return connectionFactory;
    }

    @Bean(name="leaveMsgTemplate")
    public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
/*		RetryTemplate retryTemplate = new RetryTemplate();
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(500);
		backOffPolicy.setMultiplier(10.0);
		backOffPolicy.setMaxInterval(10000);
		retryTemplate.setBackOffPolicy(backOffPolicy);
		template.setRetryTemplate(retryTemplate);*/
		
		
		/*
		mandatory
		This flag tells the server how to react if the message cannot be routed to a queue. 
		If this flag is set, the server will return an unroutable message with a Return method. 
		If this flag is zero, the server silently drops the message
		 */
		template.setMandatory(true); //Needed by Publisher Returns
		
		//Only one ReturnCallback is supported by each RabbitTemplate
		template.setReturnCallback(new ReturnCallback(){

			@Override
			public void returnedMessage(Message message, int replyCode,
					String replyText, String exchange, String routingKey) {
				System.out.println("-------Unroutable Message-------" + "[Message]:" + message 
						+ " [Exchange]:" + exchange + " [RoutingKey]:"+ routingKey + " [Replytext]:" + replyText);
				
			}
			
		});
		
		//Only one ConfirmCallback is supported by a RabbitTemplate
		template.setConfirmCallback(new ConfirmCallback(){

			@Override
			public void confirm(CorrelationData correlationData, boolean ack) {
				System.out.println("-----------Confirm-----------"  + correlationData);
			}
			
		});
		
		return template;
    }
    
    @Bean(name=QUEUENAME)
    public Queue messageQueue() {
       return new Queue(QUEUENAME);
    }

    /**
     * This bean is optional,if no exchange is defined, we will rely on the default exchange.
     */
    @Bean(name=EXCHANGNAME)
    public TopicExchange exchange(){
    	return new TopicExchange(EXCHANGNAME);
    }
    
    /**
     *This bean is optional,if no bing is defined, we will relying on the default binding 
     *to the default change,and the default binding of all queues to the default exchange by their name. 
     */
    @Bean(name="leavebinding")
    public Binding binding(){
    	return BindingBuilder.bind(
    			messageQueue()).to(exchange()).with(ROUTEKEY);
    }
    
    @Bean(name=FORWARDQUEUE)
    public Queue forwardQueue(){
    	return new Queue(FORWARDQUEUE);
    }
    
    @Bean(name=FORWARDEXCHANGE)
    public DirectExchange forwardExchange(){
    	return new DirectExchange(FORWARDEXCHANGE);
    }
    
    
    @Bean(name="forwardbinding")
    public Binding forwarBinding(){
    	return BindingBuilder.bind(
    			forwardQueue()).to(forwardExchange()).with(FORWARDROUTINGKEY);
    }
    
    @Bean
    public AmqpAdmin amqpAdmin() {
    	RabbitAdmin admin = new RabbitAdmin(connectionFactory());
    	admin.declareExchange(exchange());
    	admin.declareQueue(messageQueue());
    	admin.declareBinding(binding()); //before declare binding, should declare exchange and queue first.
    	
    	admin.declareExchange(forwardExchange());
    	admin.declareQueue(forwardQueue());
    	admin.declareBinding(forwarBinding());
        return admin;
    }
    
}
