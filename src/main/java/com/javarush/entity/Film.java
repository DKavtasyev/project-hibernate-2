package com.javarush.entity;

import com.javarush.constants.SpecialFeatures;
import com.javarush.converters.RatingConverter;
import com.javarush.constants.Rating;
import com.javarush.converters.SpecialFeaturesConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "film")
@Getter
@Setter
public class Film implements Domain
{
	@Id
	@Column(name = "film_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short filmId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "release_year", columnDefinition = "YEAR")
	private Year year;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	@ManyToOne
	@JoinColumn(name = "original_language_id")
	private Language originalLanguage;

	@Column(name = "rental_duration", nullable = false)
	private byte rentalDuration = 3;

	@Column(name = "rental_rate", nullable = false, columnDefinition = "DECIMAL")
	private double rentalRate = 4.99;

	@Column(name = "length")
	private short length;

	@Column(name = "replacement_cost", nullable = false, columnDefinition = "DECIMAL")
	private double replacementCost = 19.99;

	@Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
	@Convert(converter = RatingConverter.class)
	private Rating rating = Rating.G;

	@Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
	@Convert(converter = SpecialFeaturesConverter.class)
	private SpecialFeatures[] specialFeatures;

	@Column(name = "last_update", nullable = false)
	@UpdateTimestamp
	private LocalDateTime lastUpdate;

	@ManyToMany
	@JoinTable(name = "film_actor",
		joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
		inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
	private Set<Actor> actors = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "film_category",
		joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
	private Set<Category> categories = new HashSet<>();
}
