package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class Payment implements Domain
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id", nullable = false)
	private Short paymentId;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;

	@OneToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;

	@Column(name = "amount", nullable = false, columnDefinition = "DECIMAL")
	private double amount;

	@Column(name = "payment_date", nullable = false)
	private LocalDateTime paymentDate;

	@UpdateTimestamp
	@Column(name = "last_update")
	@Basic
	private LocalDateTime lastUpdate;
}
