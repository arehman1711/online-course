package com.onlineCourse.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Lazy(value = false)
@Table(name="COURSE")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope= Course.class)
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
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE}, fetch = FetchType.EAGER,
			mappedBy = "courseList")
	private List<User> enrolledUsers = new ArrayList<>();

	@Transient
	private boolean isEnrolled;

	@Transient
	private String imageId;

	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", courseName='" + courseName + '\'' +
				", courseDescription='" + courseDescription + '\'' +
				", duration=" + duration +
				", trainer='" + trainer + '\'' +
				", isEnrolled=" + isEnrolled +
				'}';
	}
}
