package com.onlineCourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlineCourse.entities.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User getUserByEmail(String email);

}
