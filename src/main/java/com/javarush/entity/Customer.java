package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer implements Domain
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id", nullable = false)
	private short customerId;

	@ManyToOne
	@JoinColumn(name = "store_id", nullable = false)		// TODO Уточнить, можно ли указывать одновременно связь ManyToOne и OneToMany
	private Store store;

	@Column(name = "first_name", length = 45, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 45, nullable = false)
	private String lastName;

	@Column(name = "email", length = 50)
	private String email;

	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;

	@Column(name = "active", nullable = false)
	@Basic
	private boolean active = true;									// TODO Уточнить значение по умолчанию

	@CreationTimestamp
	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;						// TODO Уточнить тип

	@UpdateTimestamp
	@Column(name = "last_update")
	@Basic
	private LocalDateTime lastUpdate = LocalDateTime.now();
}
