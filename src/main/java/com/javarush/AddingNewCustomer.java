package com.javarush;

import com.javarush.builder.AddressBuilder;
import com.javarush.builder.CustomerBuilder;
import com.javarush.dao.MovieDAO;
import com.javarush.entity.Address;
import com.javarush.entity.City;
import com.javarush.entity.Country;
import com.javarush.entity.Customer;
import com.javarush.factory.MySessionFactory;
import org.hibernate.Session;

public class AddingNewCustomer
{
	public static void main(String[] args)
	{
		try(Session session = MySessionFactory.getSessionFactory().openSession())
		{
			City city = new City();
			city.setCity("Karaganda");
			city.setCountry(session.get(Country.class, 51));

			Address address = new AddressBuilder(session)
					.addAddress("Pushkin street")
					.addDistrict("Kolotushkino")
					.addCity(city)
					.addPostalCode("123456")
					.addPhone("+79991111111")
					.buildAddress();

			Customer customer = new CustomerBuilder(session)
					.addStoreId(1)
					.addFirstName("Petya")
					.addLastName("Ivanov")
					.addEmail("petya.ivanov@mail.ru")
					.addAddress(address)
					.buildCustomer();

			MovieDAO movieDAO = new MovieDAO(session);
			movieDAO.addCustomer(customer);
		}
	}
}
