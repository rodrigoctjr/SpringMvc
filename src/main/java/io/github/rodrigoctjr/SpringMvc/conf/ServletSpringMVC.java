package io.github.rodrigoctjr.SpringMvc.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return null;
	}

	/*
	 * Faz o mapeamento das classes e controles que serao carregados para resolver as urls. 
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class[] {AppWebConfiguration.class};
	}
	
	
	/*
	 * Qual o pradrao de endereco que sera destinado para o servles
	 * Equivalente ao <url-mapping> no web.xml
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */	
	@Override
	protected String[] getServletMappings()
	{
		return new String[] {""};
	}
}
