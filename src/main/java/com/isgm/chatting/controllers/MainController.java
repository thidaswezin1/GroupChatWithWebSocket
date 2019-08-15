package com.isgm.chatting.controllers;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.isgm.chatting.entity.User;
import com.isgm.chatting.repository.UserRepository;

@Controller
public class MainController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/chat")
	public String chatting( Model model, Authentication auth) {
		//String username = (String) request.getSession().getAttribute("name");
		//String username = user.getName();
		if (auth.isAuthenticated()) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			
		
		User loggedInUser = userRepo.findByName(userDetail.getUsername());
		model.addAttribute("username", loggedInUser.getName());
		System.out.println("username>>>>>in "+loggedInUser.getName());
		}
		return "chatting";
	}
	
	@GetMapping("/login")
	public String main(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";	
	}
	
	@PostMapping("/signup")
	public String signUp(User user,Model model) {
		
		List<User> list = userRepo.findByOwnQuery(user.getName());
		System.out.println("list "+list.size());
		if(list.size()!=0) {
			model.addAttribute("existUser",user.getName());
	
			System.out.println("Exist User "+user.getName());
			
			return "redirect:/";
		}
		else {
				
			String encodePassword = encoder.encode(user.getPassword());
			user.setPassword(encodePassword);
			
			userRepo.save(user);
			return "redirect:/login"; 
		}
	}
	
	@RequestMapping(path = "/logout")
    public String logout() {
         
        return "redirect:/login";
    }
}

