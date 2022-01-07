package cl.informacion;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "viaticoEntityManagerFactory", transactionManagerRef = "viaticoTransactionManager", 
	basePackages = { "cl.carabineros.model.dao.viatico"})
public class ViaticoConfig {
	
	@Autowired
	private Environment env;
	
	@Bean(name = "viaticoDataSource")
	public DataSource viaticoDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("viatico.datasource.url"));
		dataSource.setUsername(env.getProperty("viatico.datasource.username"));
		dataSource.setPassword(env.getProperty("viatico.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("viatico.datasource.driver-class-name"));
		
		return dataSource;
	}
	
	@Bean(name = "viaticoEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(viaticoDatasource());
		em.setPackagesToScan("cl.carabineros.model.viatico");
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("viatico.jpa.hibernate.ddl-auto"));
		//properties.put("hibernate.show-sql", env.getProperty("mysql.jpa.show-sql"));
		//properties.put("hibernate.dialect", env.getProperty("mysql.jpa.database-platform"));
		
		em.setJpaPropertyMap(properties);
		
		return em;
		
	}
	
	@Bean(name = "viaticoTransactionManager")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		
		return transactionManager;
	}
	
	
	

}
