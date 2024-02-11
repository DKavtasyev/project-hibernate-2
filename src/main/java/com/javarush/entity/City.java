package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "city")
@Getter
@Setter
public class City implements Domain
{
	@Id
	@Column(name = "city_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short cityId;

	@Column(name = "city", length = 50, nullable = false)
	private String city;

	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

	@UpdateTimestamp
	@Basic
	@Column(name = "last_update", nullable = false)
	private LocalDateTime lastUpdate = LocalDateTime.now();
}
