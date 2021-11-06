package com.luv2code.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/customer")
public class CustomerController{
	
	//add an initbinder to convert trim input strings
	//remove leading and trailing whitespace 
	//if only whitespace, trim to null
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); 
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor); 
	}
	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		
		theModel.addAttribute("customer", new Customer());
		
		return "customer-form";
	}
	
	
	@RequestMapping("/processForm")
	//Validate Customer Object using @Valid annotation
	//Spring stores result of validation in BindingResult Object
	public String processForm( @Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {
		//System.out.println("here 1");
		
		System.out.println("Binding Result: " + theBindingResult + "\n\n\n\n\n\n");
		
		
		if(theBindingResult.hasErrors()) {
			//System.out.println("here 2");
			return "customer-form"; 
		}
		else {
			//System.out.println("here 3");
			return "customer-confirmation"; 
		}
	}
}