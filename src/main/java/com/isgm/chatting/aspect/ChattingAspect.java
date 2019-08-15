package com.isgm.chatting.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.isgm.chatting.repository.UserRepository;


  @Aspect
  
  @Component
  public class ChattingAspect {
  
  @Autowired 
  UserRepository userRepo;
  
  @Before("target(com.isgm.chatting.controllers.MainController) && @annotation(org.springframework.web.bind.annotation.GetMapping)"
		  )
  public void beforeController(JoinPoint jp)
  	  {
	  Model model =(Model)jp.getArgs()[0]; 
	  model.addAttribute("text", "SmartGroup"); 
	  }
  
	/*
	 * @After("target(com.isgm.chatting.controllers.MainController) && @annotation(org.springframework.web.bind.annotation.RequestMapping)"
	 * +
	 * " && execution(* com.isgm.chatting.controllers.MainController.*(org.springframework.ui.Model,..))"
	 * ) public void afterController(JoinPoint jp) {
	 * 
	 * Model model1 =(Model)jp.getArgs()[0]; model1.addAttribute("hai", "Hello");
	 * 
	 * 
	 * }
	 */
  }
 
