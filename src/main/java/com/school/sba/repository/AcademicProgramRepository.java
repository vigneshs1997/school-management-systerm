package com.school.sba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.sba.entity.AcademicProgram;

@Repository
public interface AcademicProgramRepository extends JpaRepository<AcademicProgram, Integer>{
	
	/*JpaRepository contains only CRUD operations*/
	
	/*==========================we create our own operation other than CRUD operation=======================================*/
	List<AcademicProgram> findByIsDeleted(boolean b);
}
