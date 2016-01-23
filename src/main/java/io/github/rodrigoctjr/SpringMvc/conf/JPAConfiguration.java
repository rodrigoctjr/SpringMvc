package io.github.rodrigoctjr.SpringMvc.conf;



import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration 
{

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		/*
		 * The local container works like the persistence.xml 
		 */
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] {"io.github.rodrigoctjr.SpringMvc.model"});
		
		/*
		 * The jpa vendor represents the implementation of JPA (ORM).
		 * The ORM Spring supports by default the EclipseLink and the OpenJpa
		 */
		JpaVendorAdapter vendorAdapter =  new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		
		return em;
	}
		
	/*
	 * The 'Bean' annotation is to indicate that these objects are controlled by the Spring
	 * and can be injected.
	 */
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/springMvc");
		dataSource.setUsername("postgres");
		dataSource.setPassword("123");
		return dataSource;		
	}
	
	private Properties additionalProperties()
	{
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("hibernate.show_sql","true");
		return properties;								
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
			
	}
}
