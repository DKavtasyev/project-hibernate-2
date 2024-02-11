package com.javarush.builder;

import com.javarush.constants.Rating;
import com.javarush.constants.SpecialFeatures;
import com.javarush.entity.*;
import org.hibernate.Session;

import java.time.Year;
import java.util.Collection;

public class FilmBuilder
{
	private final Film film;
	private final Session session;

	public FilmBuilder(Session session)
	{
		film = new Film();
		this.session = session;
	}

	public FilmBuilder addTitle(String title)
	{
		film.setTitle(title);
		return this;
	}

	public FilmBuilder addDescription(String description)
	{
		film.setDescription(description);
		return this;
	}

	public FilmBuilder addReleaseYear(int intYear)
	{
		Year year = Year.of(intYear);
		film.setYear(year);
		return this;
	}

	public FilmBuilder addLanguage(Language language)
	{
		film.setLanguage(language);
		return this;
	}

	public FilmBuilder addOrdinalLanguage(Language language)
	{
		film.setOriginalLanguage(language);
		return this;
	}

	public FilmBuilder addRentalDuration(byte duration)
	{
		film.setRentalDuration(duration);
		return this;
	}

	public FilmBuilder addLength(int length)
	{
		film.setLength((short) length);
		return this;
	}

	public FilmBuilder addReplacementCost(double replacementCost)
	{
		film.setReplacementCost(replacementCost);
		return this;
	}

	public FilmBuilder addRating(Rating rating)
	{
		film.setRating(rating);
		return this;
	}

	public FilmBuilder addSpecialFeatures(SpecialFeatures...features)
	{
		film.setSpecialFeatures(features);
		return this;
	}

	public FilmBuilder addActor(Actor actor)
	{
		film.getActors().add(actor);
		return this;
	}

	public FilmBuilder addActor(Collection<Actor> actors)
	{
		film.getActors().addAll(actors);
		return this;
	}

	public FilmBuilder addCategory(Category category)
	{
		film.getCategories().add(category);
		return this;
	}

	public FilmBuilder addCategory(int category_id)
	{
		film.getCategories().add(session.get(Category.class, (byte) category_id));
		return this;
	}

	public Film buildFilm()
	{
		return film;
	}
}
