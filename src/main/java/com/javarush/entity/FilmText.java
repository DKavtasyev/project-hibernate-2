package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Table(name = "film_text")
@Getter
@Setter
public class FilmText implements Domain
{
	@Id
	@Column(name = "film_id")		//TODO Выяснить, один ли и тот же id на таблицы film и film_text
	private short filmId;

	@OneToOne
	@JoinColumn(name = "film_id")
	private Film film;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
}
