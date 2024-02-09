package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actor")
@Getter
@Setter
public class Actor implements Domain
{
	@Id
	@Column(name = "actor_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short actorId;

	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;

	@UpdateTimestamp
	@Column(name = "last_update", nullable = false)
	@Basic
	private LocalDateTime lastUpdate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "film_actor",
		joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"),
		inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
	private Set<Film> films = new HashSet<>();
}
