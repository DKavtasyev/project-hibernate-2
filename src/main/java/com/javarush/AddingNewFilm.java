package com.javarush;

import com.javarush.builder.FilmBuilder;
import com.javarush.constants.Rating;
import com.javarush.dao.MovieDAO;
import com.javarush.entity.Actor;
import com.javarush.entity.Film;
import com.javarush.entity.Language;
import com.javarush.factory.MySessionFactory;
import org.hibernate.Session;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.javarush.constants.SpecialFeatures.*;

public class AddingNewFilm
{
	public static void main(String[] args)
	{
		try(Session session = MySessionFactory.getSessionFactory().openSession())
		{
			Language language = session.get(Language.class, 1);

			Actor actor1 = session.get(Actor.class, 5);
			Actor actor2 = session.get(Actor.class, 25);
			Actor actor3 = new Actor();
			actor3.setFirstName("JULIO");
			actor3.setLastName("FERNANDES");

			Set<Actor> actors = new HashSet<>();
			Collections.addAll(actors, actor1, actor2, actor3);

			Film film = new FilmBuilder(session)
					.addTitle("EXCITING CODING")
					.addDescription("A fascinating drama about growing Senior Java Backend Developer")
					.addReleaseYear(2024)
					.addLanguage(language)
					.addLength(180)
					.addRating(Rating.NC17)
					.addSpecialFeatures(TRAILERS, COMMENTARIES, DELETED_SCENES, BEHIND_THE_SCENES)
					.addActor(actors)
					.addCategory(1)
					.addCategory(7)
					.addCategory(14)
					.buildFilm();

			MovieDAO movieDAO = new MovieDAO(session);
			int film_id = movieDAO.addNewFilm(film);
			System.out.println("film id: " + film_id);
		}
	}
}
