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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;


@Data
@Entity
@Table(name="USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "StudentId")
	private int studentId;
	
	@Column(name = "name")
	private String name;
	
	@NonNull

	@Column(name = "email")
	private String email;
	
	@NonNull
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "imagegurl")
	private String imageurl;
  @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
private List<Course> courses=new ArrayList<>();
		
	}
