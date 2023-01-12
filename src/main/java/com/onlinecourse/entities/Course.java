package com.onlinecourse.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="COURSE")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "courseId")
	private int courseId;
	
	@Column(name = "role")
	private String name;
	
	@Column(name = "duration")
	private String duration;
}
