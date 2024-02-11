package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "country")
@Getter
@Setter
public class Country implements Domain
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id", nullable = false)
	private Short countryId;

	@Column(name = "country", length = 50, nullable = false)
	private String country;

	@UpdateTimestamp
	@Basic
	@Column(name = "last_update", nullable = false)
	private LocalDateTime lastUpdate = LocalDateTime.now();
}
