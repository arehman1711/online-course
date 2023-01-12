package com.onlinecourse.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "studentId")
	private int studentId;

	@Column(name = "name")
	@Size(min = 10, max = 200, message = "Name must be between 10 and 200 characters")
	private String name;

	@NotNull(message = "Name cannot be null")
	@Email(message = "Email should be valid")
	@Column(name = "email")
	private String email;

	@Column(name = "role")
	private String role;

	@NotNull(message = "Password cannot be null")
	@Column(name = "password")
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Course> courses = new ArrayList<>();;

}
