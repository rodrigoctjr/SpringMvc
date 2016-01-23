package io.github.rodrigoctjr.SpringMvc.validation;

import io.github.rodrigoctjr.SpringMvc.model.Product;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/*
 * Put the validation code on controller isn't cool, because
 * 
 * we add a responsibility that not belongs to the class. So we will create 
 * 
 * a specify class to do this. The spring give a interface that do this.
 */

public class ProductValidator implements Validator{

	
	/*
	 * This method is the way that the spring can call a specific validator.
	 * The class that is being validated is passed as parameter and the spring will
	 * return if the validator can resolve this.
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {		
		return Product.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		// The third parameter is responsible to indicate which errors will be show
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "releaseDate", "field.required");
		Product product = (Product) object;
		
		
		
		/*
		 *  Case the validation don't exist on the ValidationUtils, we can do the validation
		 *  and add the message with rejectValue
		 */
		
		if(product.getPages() == 0)
			errors.rejectValue("pages", "field.required");
	}
	
	
	

}
