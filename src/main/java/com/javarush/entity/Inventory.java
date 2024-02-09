package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter
@Setter
public class Inventory implements Domain
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_id", nullable = false)
	private int inventoryId;

	@ManyToOne
	@JoinColumn(name = "film_id", nullable = false)
	private Film film;

	@ManyToOne
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	@UpdateTimestamp
	@Basic
	@Column(name = "last_update", nullable = false)
	private LocalDateTime lastUpdate = LocalDateTime.now();

	@Override
	public String toString()
	{
		return "inventory id: " + inventoryId + "\n" +
				"film id: " + film.getFilmId() + "\n" +
				"film name: " + film.getFilmText().getTitle() + "\n" +
				"store id: " + store.getStoreId() + "\n";
	}
}
