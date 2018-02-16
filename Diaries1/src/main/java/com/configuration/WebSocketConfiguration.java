package com.configuration;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
@ComponentScan(basePackages = "com")
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer
{

	public void registerStompEndpoints(StompEndpointRegistry registry) 
	{
		System.out.println("Registering STOMP ENDPOINTS");
		registry.addEndpoint("/chat");
	}
	
	 public void configureMessageBroker(MessageBrokerRegistry config)
	 {
		 	System.out.println("Registering Message Broker End points");
		    config.enableSimpleBroker("/topic/");
		    config.setApplicationDestinationPrefixes("/app");
	 }
	
}