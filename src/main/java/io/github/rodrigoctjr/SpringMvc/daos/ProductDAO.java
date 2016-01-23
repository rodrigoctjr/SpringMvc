package io.github.rodrigoctjr.SpringMvc.daos;

import java.util.List;

import io.github.rodrigoctjr.SpringMvc.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
	@PersistenceContext
	EntityManager manager;

	public void save(Product product) {
		manager.persist(product);
	}

	/*
	 * Annotation types
	 * 
	 * @Component: the class is a spring's beans.
	 * 
	 * @Repository: the class is responsible for the data access
	 * 
	 * @Controller: the class interacts with the requests from the web.
	 * 
	 * @Service: the class it's on a some business rule.
	 */

	public List<Product> list() {
		List<Product> retorno = manager.createQuery(
				"select distinct(p) from Product p join fetch p.prices",
				Product.class).getResultList();
		
		return retorno;
	}
	
	

}
