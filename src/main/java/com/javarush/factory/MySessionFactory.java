package com.javarush.factory;

import com.javarush.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class MySessionFactory implements AutoCloseable
{
	private final SessionFactory sessionFactory;
	private static MySessionFactory instance;

	private MySessionFactory()
	{
		Properties properties = new Properties();
		properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//		properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306/movie");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "ProVoLokA");
		properties.put(Environment.SHOW_SQL, true);
		properties.put(Environment.HBM2DDL_AUTO, "validate");

		sessionFactory = new Configuration()
				.addProperties(properties)
				.addAnnotatedClass(Actor.class)
				.addAnnotatedClass(Address.class)
				.addAnnotatedClass(Category.class)
				.addAnnotatedClass(City.class)
				.addAnnotatedClass(Country.class)
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(Film.class)
				.addAnnotatedClass(FilmText.class)
				.addAnnotatedClass(Inventory.class)
				.addAnnotatedClass(Language.class)
				.addAnnotatedClass(Payment.class)
				.addAnnotatedClass(Rental.class)
				.addAnnotatedClass(Staff.class)
				.addAnnotatedClass(Store.class)
				.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory()
	{
		if(instance == null)
			instance = new MySessionFactory();
		return instance.sessionFactory;
	}

	@Override
	public void close()
	{
		sessionFactory.close();
	}
}
