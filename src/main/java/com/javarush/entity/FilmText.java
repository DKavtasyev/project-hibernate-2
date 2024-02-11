package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "film_text")
@Getter
@Setter
public class FilmText implements Domain
{
	@Id
	@Column(name = "film_id")
	private Short filmId;

	@OneToOne
	@JoinColumn(name = "film_id")
	private Film film;

	@Column(name = "title")
	private String title;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
}
