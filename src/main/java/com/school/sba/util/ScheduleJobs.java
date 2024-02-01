//package com.school.sba.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
///*======================particular time(a day or a week) the bottom task will be exicuted automatically==============================*/
//
//import com.school.sba.repository.AcademicProgramRepository;
//import com.school.sba.repository.ClassHourRepository;
//import com.school.sba.repository.UserRepository;
//import com.school.sba.serviceImpl.AcademicProgramServiceImpl;
//import com.school.sba.serviceImpl.SchoolServiceImpl;
//import com.school.sba.serviceImpl.UserServiceImpl;
//
//@Component // for creating bean class,so that @EnableScheduling finds @Component
//public class ScheduleJobs {
///*====================================================================================================================================*/
//	
//	@Autowired
//	private UserServiceImpl userServiceImpl;
//	
//	@Autowired
//	private AcademicProgramServiceImpl academicProgramServiceImpl;
//	
//	@Autowired
//	private SchoolServiceImpl schoolServiceImpl;
//	
//	@Scheduled(fixedDelay = 2000L)
//	public void hardDelete() {
//		userServiceImpl.hardDeleteUser();
//		academicProgramServiceImpl.hardDeleteAcademicProgram();
//		schoolServiceImpl.hardDeleteSchool();
//	}
//	
//
//}
