package io.github.rodrigoctjr.SpringMvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController 
{
	@RequestMapping("/home")
	public void index()
	{
		System.out.println("Carregando os produtos");
		System.out.println("volta");
	}
	
	
}
