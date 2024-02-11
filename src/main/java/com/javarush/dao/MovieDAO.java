package com.javarush.dao;

import com.javarush.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

public class MovieDAO
{
	private final Session session;

	public MovieDAO(Session session)
	{
		this.session = session;
	}

	public void addCustomer(Customer customer)
	{
		Transaction transaction = session.getTransaction();
		try
		{
			transaction.begin();
			session.persist(customer.getAddress().getCity().getCountry());
			session.persist(customer.getAddress().getCity());
			session.persist(customer.getAddress());
			session.persist(customer);
			transaction.commit();
		}
		catch (Exception e)
		{
			if (transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK)
			{
				transaction.rollback();
				e.printStackTrace();
			}
		}
	}

	public void returnFilm(int rental_id)
	{
		Rental rental = session.get(Rental.class, rental_id);

		if (rental.getReturnDate() != null)
			throw new NoSuchElementException("Rent with id = " + rental_id + " not found or already has been returned!");

		rental.setReturnDate(LocalDateTime.now());
		Transaction transaction = session.beginTransaction();
		transaction.commit();
	}

	public int rentFilm(Customer customer, int store_id, int film_id)
	{
		List<Inventory> inventories = getAvailableInventories(store_id, film_id);
		Inventory inventory = inventories.get(0);
		Film film = inventory.getFilm();
		Store store = inventory.getStore();

		Staff staff = store.getManagerStaff();

		Rental rental = new Rental();
		rental.setRentalDate(LocalDateTime.now());
		rental.setInventory(inventory);
		rental.setCustomer(customer);
		rental.setStaff(staff);

		Payment payment = new Payment();
		payment.setCustomer(customer);
		payment.setStaff(staff);
		payment.setRental(rental);
		payment.setAmount(film.getRentalRate());
		payment.setPaymentDate(LocalDateTime.now());

		Transaction transaction = session.getTransaction();
		try
		{
			transaction.begin();
			session.persist(rental);
			session.persist(payment);
			transaction.commit();
			rental.setRentalDate(LocalDateTime.now());
			rental.setReturnDate(null);
			session.flush();
		}
		catch(Exception e)
		{
			if (transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK)
			{
				transaction.rollback();
				e.printStackTrace();
			}
		}
		return rental.getRentalId();
	}

	private List<Inventory> getAvailableInventories(int store_id, int film_id)
	{
		String sql = "SELECT i.* FROM inventory i JOIN store s ON i.store_id = s.store_id " +
				"WHERE film_id = :film_id AND s.store_id = :store_id AND inventory_id NOT IN (" +
				"SELECT i.inventory_id FROM inventory i JOIN rental r ON i.inventory_id = r.inventory_id " +
				"WHERE return_date IS NULL AND film_id = :film_id AND store_id = :store_id)";

		NativeQuery<Inventory> nativeQuery = session.createNativeQuery(sql, Inventory.class);
		nativeQuery.setParameter("film_id", film_id);
		nativeQuery.setParameter("store_id", store_id);

		return nativeQuery.list();
	}

	public int addNewFilm(Film film)
	{
		Transaction transaction = session.getTransaction();
		try
		{
			transaction.begin();
			session.persist(film.getLanguage());

			if(film.getOriginalLanguage() != null)
			{
				session.persist(film.getOriginalLanguage());
			}

			for(Actor actor: film.getActors())
				session.persist(actor);

			for(Category category: film.getCategories())
				session.persist(category);

			session.persist(film);

			FilmText filmText = new FilmText();
			filmText.setFilm(film);
			filmText.setFilmId(film.getFilmId());
			filmText.setTitle(film.getTitle());
			filmText.setDescription(film.getDescription());
			session.persist(filmText);
			transaction.commit();
		}
		catch(Exception e)
		{
			if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK)
			{
				transaction.rollback();
				e.printStackTrace();
			}
		}
		return film.getFilmId();
	}
}
