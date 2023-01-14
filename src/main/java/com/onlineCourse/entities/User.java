package com.onlineCourse.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
@Data
@Entity
@Lazy(value = false)
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	@Size(min = 10, max = 200, message = "Name must be between 10 and 200 characters")
	private String name;

	@NotNull(message = "Email is Mandatory.")
	@Email(message = "Email should be valid")
	@Column(name = "email")
	private String email;

	@Column(name = "role")
	private String role = "user";

	@NotNull(message = "Password cannot be Empty.")
	@Column(name = "password")
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "course_enrollment",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courseList = new ArrayList<>();

}
