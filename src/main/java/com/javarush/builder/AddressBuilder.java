package com.javarush.builder;

import com.javarush.entity.Address;
import com.javarush.entity.City;
import org.hibernate.Session;

public class AddressBuilder
{
	private final Address address;
	private final Session session;

	public AddressBuilder(Session session)
	{
		address = new Address();
		this.session = session;
	}

	public AddressBuilder addAddress(String addrString)
	{
		address.setAddress(addrString);
		return this;
	}

	public AddressBuilder addAddress2(String addrString)
	{
		address.setAddress2(addrString);
		return this;
	}

	public AddressBuilder addDistrict(String district)
	{
		address.setDistrict(district);
		return this;
	}

	public AddressBuilder addCity(City city)
	{
		address.setCity(city);
		return this;
	}

	public AddressBuilder addCity(int city_id)
	{
		address.setCity(session.get(City.class, (short) city_id));
		return this;
	}

	public AddressBuilder addPostalCode(String postalCode)
	{
		address.setPostalCode(postalCode);
		return this;
	}

	public AddressBuilder addPhone(String phone)
	{
		address.setPhone(phone);
		return this;
	}

	public Address buildAddress()
	{
		return address;
	}
}
