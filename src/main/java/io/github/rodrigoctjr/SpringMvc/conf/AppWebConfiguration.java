package io.github.rodrigoctjr.SpringMvc.conf;

import io.github.rodrigoctjr.SpringMvc.controller.HomeController;
import io.github.rodrigoctjr.SpringMvc.daos.ProductDAO;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProductDAO.class})
/*@ComponentScan(basePackages = 	{	"io.github.rodrigoctjr.SpringMvc.controller", 
									"io.github.rodrigoctjr.SpringMvc.daos"
								})*/
public class AppWebConfiguration 
{
	/*
	 * Has a goal of show for the servlet wich are the classes and controls
	 */
	
	
	/*
	 * The annotation EnableWebMvc is responsible to XML,JSON convertions and others.   
	 */
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver()
	{
		/*
		 * This method save settings of the base folder and sufix.
		 * This informations are add to the path returned by the controllers.		 
		 */
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		System.out.println("TESTE12 - RESOLVER");
		return resolver;				
	}	
	
	/*
	 * This method set the file configuration of the messages that will be show
	 * in the view
	 */
	@Bean (name = "messageSource")
	public MessageSource loadBundle()
	{
		System.out.println("TESTE12 - MESSAGES");
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		bundle.setCacheSeconds(1);
		return bundle;				
	}	
	
	/*
	 * This method is used to avoid that new configuration will be done for each
	 * date type.
	 */
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(
				true);

		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}
	
}
