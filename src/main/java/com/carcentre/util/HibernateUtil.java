
package com.carcentre.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Clase para inicializacion de Session Factory Hibernate
 * @author diego
 *
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
			cfg.addAnnotatedClass(com.carcentre.model.Mecanico.class);

			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
			builder.applySettings(cfg.getProperties());
			StandardServiceRegistry registry = builder.build();
			
			sessionFactory = cfg.buildSessionFactory(registry);
		} catch (Throwable e) {
			System.err.println("Error al crear SessionFactory" + e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}
	
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
