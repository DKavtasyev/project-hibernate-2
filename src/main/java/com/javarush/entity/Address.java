package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address implements Domain
{
	@Id
	@Column(name = "address_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short addressId;

	@Column(name = "address", nullable = false, length = 50)
	private String address;

	@Column(name = "address2", length = 50)
	private String address2;

	@Column(name = "district", nullable = false, length = 20)
	private String district;

	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;

	@Column(name = "postal_code", length = 10)
	private String postalCode;

	@Column(name = "phone", nullable = false, length = 20)
	private String phone;

	@UpdateTimestamp
	@Column(name = "last_update", nullable = false)
	@Basic
	private LocalDateTime lastUpdate;
}
