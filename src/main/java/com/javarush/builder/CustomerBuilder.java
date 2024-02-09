package com.javarush.builder;

import com.javarush.entity.Address;
import com.javarush.entity.Customer;
import com.javarush.entity.Store;
import org.hibernate.Session;

public class CustomerBuilder
{
	private final Customer customer;
	private final Session session;

	public CustomerBuilder(Session session)
	{
		customer = new Customer();
		this.session = session;
	}

	public CustomerBuilder addStoreId(int id)
	{
		customer.setStore(session.get(Store.class, id));
		return this;
	}

	public CustomerBuilder addFirstName(String firstName)
	{
		customer.setFirstName(firstName);
		return this;
	}

	public CustomerBuilder addLastName(String lastName)
	{
		customer.setLastName(lastName);
		return this;
	}

	public CustomerBuilder addEmail(String email)
	{
		customer.setEmail(email);
		return this;
	}

	public CustomerBuilder addAddress(Address address)
	{
		customer.setAddress(address);
		return this;
	}

	public CustomerBuilder addAddress(int id)
	{
		customer.setAddress(session.get(Address.class, id));
		return this;
	}

	public CustomerBuilder isActive(boolean b)
	{
		customer.setActive(b);
		return this;
	}

	public Customer buildCustomer()
	{
		return customer;
	}

}
