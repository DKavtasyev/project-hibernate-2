package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental")
@Getter
@Setter
public class Rental implements Domain
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rental_id", nullable = false)
	private int rentalId;

	@Column(name = "rental_date", nullable = false)
	private LocalDateTime rentalDate;

	@ManyToOne
	@JoinColumn(name = "inventory_id", nullable = false)
	private Inventory inventory;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Column(name = "return_date")
	private LocalDateTime returnDate;

	@ManyToOne
	@JoinColumn(name = "staff_id", nullable = false)
	private Staff staff;

	@UpdateTimestamp
	@Column(name = "last_update", nullable = false)
	@Basic
	private LocalDateTime lastUpdate;
}
