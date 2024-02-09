package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "language")
@Getter
@Setter
public class Language implements Domain
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "language_id", nullable = false)
	private byte languageId;

	@Column(name = "name", nullable = false, length = 20, columnDefinition = "CHAR")
	private String name;

	@UpdateTimestamp
	@Basic
	@Column(name = "last_update", nullable = false)
	private LocalDateTime lastUpdate = LocalDateTime.now();
}
