package com.javarush;

import com.javarush.dao.MovieDAO;
import com.javarush.factory.MySessionFactory;
import org.hibernate.Session;

public class ReturningFilm
{
	public static void main(String[] args)
	{
		try(Session session = MySessionFactory.getSessionFactory().openSession())
		{
			MovieDAO movieDAO = new MovieDAO(session);
			movieDAO.returnFilm(11496);
		}
	}
}
