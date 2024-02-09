package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category implements Domain
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", nullable = false)
	private byte categoryId;

	@Column(name = "name", nullable = false, length = 25)
	private String name;

	@UpdateTimestamp
	@Basic
	@Column(name = "last_update", nullable = false)
	private LocalDateTime lastUpdate = LocalDateTime.now();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "film_category",
		joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"),
		inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
	private Set<Film> films = new HashSet<>();
}
