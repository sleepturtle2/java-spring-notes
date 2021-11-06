package com.luv2code.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController{
	
	//need a controller method to show the initial HTML form
	
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form"; 
	}
	
	//need controller method to process HTML form 
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld"; 
	}
	
	//new controller method to read form data 
	//add data to the model 
	
	@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		//read the request parameter from the HTML form
		String theName = request.getParameter("studentName"); 
				
		//convert the data to all caps 
		theName = theName.toUpperCase(); 
		//create the message 
		String result = "YO! " + theName; 
		//add message to the model 
		model.addAttribute("message", result); 
		
		return "helloworld";
	}
	
	@RequestMapping("/processFormVersionThree")
	/*
	 * RequestParam will read the HTTP request and get the form data with the 
	 * name of studentName and bind it to variable theName
	 */
	public String processFormVersionThree(@RequestParam("studentName") String theName, Model model) {

		//convert the data to all caps 
		theName = theName.toUpperCase(); 
		//create the message 
		String result = "Hey! " + theName; 
		//add message to the model 
		model.addAttribute("message", result); 
		
		return "helloworld";
	}
}