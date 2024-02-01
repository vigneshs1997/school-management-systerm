package com.school.sba.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.sba.entity.ClassHour;
import com.school.sba.entity.User;

@Repository
public interface ClassHourRepository extends JpaRepository<ClassHour, Integer> {
	
	/*JpaRepository contains only CRUD operations*/
	
	/*==========================we create our own operation other than CRUD operation=======================================*/

//	boolean existsByClassBeginsAtBetweenClassRoomNumber(LocalDateTime classBeginsAt,
//	LocalDateTime classEndsAt, int classRoomNumber);

boolean existsByClassBeginsAtAndClassRoomNumber(LocalDateTime classBeginsAt, int classRoomNumber);

   List<ClassHour> findByUser(User user);

}
