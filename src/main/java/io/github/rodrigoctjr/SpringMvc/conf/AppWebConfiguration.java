package io.github.rodrigoctjr.SpringMvc.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.github.rodrigoctjr.SpringMvc.controller.HomeController;

@EnableWebMvc
//@ComponentScan(basePackageClasses={HomeController.class})
@ComponentScan(basePackages="br.com.estudos.springMvc.controller")
public class AppWebConfiguration 
{
	/*
	 * Has a goal of show for the servlet wich are the class and controls
	 */
	
	
	/*
	 * The annotation EnableWebMvc is responsible to XML,JSON convertions and others.   
	 */
}
