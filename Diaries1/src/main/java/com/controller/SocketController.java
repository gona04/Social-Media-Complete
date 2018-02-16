package com.controller;

import java.util.Date;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.Message;
import com.model.OutputMessage;

@Controller
@RequestMapping("/")
public class SocketController 
{
	@RequestMapping(method = RequestMethod.GET)
	  public String viewApplication() 
	{
	    return "index.html";
	  }
	
  @MessageMapping("/chat")
  @SendTo("/topic/message")
  public OutputMessage sendMessage(Message message) 
  {
    return new OutputMessage(message, new Date());
  }
}
