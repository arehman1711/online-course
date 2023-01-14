package com.onlineCourse.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Data
@Entity
@Lazy(value = false)
@Table(name="COURSE")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "courseName")
	private String courseName;

	@Column(name = "courseDescription")
	private String courseDescription;
	@Column(name = "duration")
	private int duration;

	@Column(name = "trainer")
	private String trainer;

	@ManyToMany(mappedBy = "courseList")
	private List<User> enrolledUsers = new ArrayList<>();

	@Transient
	private boolean isEnrolled;

}
