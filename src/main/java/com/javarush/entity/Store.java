package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "store")
@Getter
@Setter
public class Store implements Domain
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id", nullable = false)
	private byte storeId;

	@OneToOne
	@JoinColumn(name = "manager_staff_id")
	private Staff managerStaff;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "store_id")
	private Set<Customer> customers = new HashSet<>();
}
