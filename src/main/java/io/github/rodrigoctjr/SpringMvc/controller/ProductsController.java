package io.github.rodrigoctjr.SpringMvc.controller;

import io.github.rodrigoctjr.SpringMvc.daos.ProductDAO;
import io.github.rodrigoctjr.SpringMvc.enumerator.BookType;
import io.github.rodrigoctjr.SpringMvc.model.Product;
import io.github.rodrigoctjr.SpringMvc.validation.ProductValidator;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * The annotation @Transactional is a implementation of JavaEE
 *  like the @PersistenceContext, but the @Transactional belongs to JTA.
 */
@Controller
@Transactional
@RequestMapping("/products")
public class ProductsController {
		
	@Autowired //Injetando o DAO
	ProductDAO productDAO;
	

	/*
	 * This method reports which is the validator of the controller.
	 * The @Valid search the validator by this.
	 * 
	 * This validation can be done by the hibernate, so we don't need of this more
	 * We will use annotation on the model class to said to the spring MVC
	 * wich are the validations.
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		//binder.setValidator(new ProductValidator());		
	}
	
	
	/*
	 * 
	 * The BindingResult attribute is used to verify errors or add new errors by validation in the controller. 
	 * This atribute is also used to return erros to the page (See the jsp page)
	 * The atribute "name" of the requestMapping is used to build the url by the mvcUrl tag.
	 */
	@RequestMapping(method = RequestMethod.POST)	
	//public ModelAndView save(Product product)
	/*
	 * Case we want take reference of the atribute's name and the commandName (.jsp) 
	 * we case use the @ModelAttribute("objetoAtual").
	 */
	//public ModelAndView save (@Valid @ModelAttribute("objetoAtual") Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes )
	public ModelAndView save (@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes )	
	{		
			
		if(bindingResult.hasErrors())
			return form(product);
		productDAO.save(product);		
		//return list();
		//ModelAndView modelAndView = new ModelAndView("redirect:produtos");
		//modelAndView.addObject("sucesso", "Produto cadastrado com sucesso");
		redirectAttributes.addFlashAttribute("sucesso","Produto cadastrado com sucesso");
		return new ModelAndView("redirect:/products");
		//return modelAndView;
		
	}
	
	/*
	 * Returning the list() after the post method save, the user can reload
	 * the page and the post method will be invoked again, because the url 
	 * remains with the post(of the submition) method and not with the get(of the search),
	 *  so a new request is called from the server side, but the browser identify just one request.
	 * This is called FORWARD, and is considered a bad practice.
	 * 
	 * The best solution is force the user side doing a new request. ("redirect:produtos")
	 * With the redirect the spring send the 302 status to the browser
	 * requesting a new request to the new address. (Always redirect after post)
	 * 
	 * All objects added throws the flash attribute is available only until the next request
	 * The objects as  accessed like this: ${sucesso}
	 */
	
	
	/*
	 * Thinking that a user can access a url of any page throws the JPS,
	 * now we will put a object encapsulating the page's access. (ModelAndView)
	 */
	
	
	/*@RequestMapping("/produtos/form")
	public String form()
	{
		System.out.println("Form");
		return "products/form";
	}*/
	
	
	
	@RequestMapping("/form")
	public ModelAndView form(Product product)
	{
		ModelAndView modelAndView =
				new ModelAndView("products/form"); 	// In the constructor is informed which view that 
													//		the object will be available
		/*											
		 * Here the Spring will send the keys e values to the atual request.
		 */
		modelAndView.addObject("types",BookType.values());
		return modelAndView;
	}
	
	/*
	 * Other option - Do the same thing that the code above 
	 * I prefer the code above, because is more explicit
	 */
	/*@RequestMapping("/produtos/form")
	public String form(Model model)
	{
		model.addAttribute("types", BookType.values());
		return "products/form";
	}*/
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list()
	{
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
		/*
		 * This return type is called PUSH because the controller send
		 * to the view the values.
		 * The JSF has a PULL method because the view ask to the controller. 
		 */
	
	}
	
	
	
	
		
	
}
