package com.pradeep.menu.util.hibernate;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.orm.User;
import com.pradeep.menu.bean.orm.UserType;

@Component
public class HibernateUtil {
	private static final SessionFactory concreteSessionFactory;

	static {
		try {
			Properties prop = new Properties();
			prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/emenu");
			prop.setProperty("hibernate.connection.username", "root");
			prop.setProperty("hibernate.connection.password", "root");
			prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
			prop.setProperty("hibernate.id.new_generator_mappings", "false");
			concreteSessionFactory = new Configuration().addPackage("com.pradeep.menu.bean.orm")
					.addProperties(prop).addAnnotatedClass(UserType.class).addAnnotatedClass(User.class)
					// .addAnnotatedClass(UserType.class)
					.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return concreteSessionFactory.openSession();
	}

	

}
