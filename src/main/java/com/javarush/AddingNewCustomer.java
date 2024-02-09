package com.javarush;

import com.javarush.builder.AddressBuilder;
import com.javarush.builder.CustomerBuilder;
import com.javarush.dao.MovieDAO;
import com.javarush.entity.Address;
import com.javarush.entity.Customer;
import com.javarush.factory.MySessionFactory;
import org.hibernate.Session;

public class AddingNewCustomer
{
	public static void main(String[] args)
	{
		try(Session session = MySessionFactory.getSessionFactory().openSession())
		{
			Address address = new AddressBuilder(session)
					.addAddress("56 Pushkin street")
					.addDistrict("Biryulyovo")
					.addCity(343)
					.addPostalCode("123456")
					.addPhone("+79991111111")
					.buildAddress();

			Customer customer = new CustomerBuilder(session)
					.addStoreId(1)
					.addFirstName("Vasya")
					.addLastName("Pupkin")
					.addEmail("vasya.pupkin@mail.ru")
					.addAddress(address)
					.buildCustomer();

			MovieDAO movieDAO = new MovieDAO(session);
			movieDAO.addCustomer(customer);
		}
	}
}
