package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "staff")
@Getter
@Setter
public class Staff implements Domain
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id", nullable = false)
	private byte staffId;

	@Column(name = "first_name", length = 45, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 45, nullable = false)
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@Lob
	@Column(name = "picture", columnDefinition = "BLOB")
	private byte[] picture;

	@Column(name = "email", length = 50)
	private String email;

	@OneToOne
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	@Column(name = "active", nullable = false)
	@Basic
	private boolean active;

	@Column(name = "username", nullable = false, length = 16)
	private String username;

	@Column(name = "password", length = 40)
	private String password;

	@UpdateTimestamp
	@Column(name = "last_update", nullable = false)
	@Basic
	private LocalDateTime lastUpdate;
}
