package com.javarush;

import com.javarush.dao.MovieDAO;
import com.javarush.entity.Customer;
import com.javarush.factory.MySessionFactory;
import org.hibernate.Session;

public class RentingFilm
{
	public static void main(String[] args)
	{
		try(Session session = MySessionFactory.getSessionFactory().openSession())
		{
			MovieDAO movieDAO = new MovieDAO(session);
			Customer customer = session.get(Customer.class, 1);
			int id = movieDAO.rentFilm(customer, 1, 6);
			System.out.println("rental_id: " + id);
		}
	}
}