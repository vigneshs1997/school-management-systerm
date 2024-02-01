package com.school.sba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.sba.entity.School;


@Repository
public interface SchoolRepository extends JpaRepository<School, Integer>{

	boolean existsByIsDeleted(boolean deleted);
	
	List<School> findByIsDeleted(boolean b);
}
