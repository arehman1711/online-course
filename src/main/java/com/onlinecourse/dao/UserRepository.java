package com.onlinecourse.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.onlinecourse.entities.User;

//@RepositoryRestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	//@Query("select u from User u where u.email=:email")
	public User getUserByEmail(String email);

}
