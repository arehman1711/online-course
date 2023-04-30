package com.onlineCourse.repository;

import com.onlineCourse.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RepositoryRestResource(exported = false)
@Repository
public interface EnrollmentRepository {
	/*@PersistenceContext
	EntityManager entityManager = null;
	@Transactional
	default void insertWithQuery(int studentId, int courseId) {
		entityManager.createNativeQuery("INSERT INTO course_enrollment (student_id, course_id) VALUES (?,?,?)")
				.setParameter(1, studentId)
				.setParameter(2, courseId)
				.executeUpdate();
	}*/
}
