package com.school.sba.entity;

import java.time.LocalDate;
import java.util.List;

import com.school.sba.enums.ProgramType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicProgram {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int programId;
	
	@Enumerated(EnumType.STRING)
	private ProgramType programType;
	
	private String programName;
	private LocalDate programBeginsAt;
	private LocalDate programEndsAt;
	private boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "schoolId")
	private School school;
	
	@ManyToMany
//	@JoinColumn(name = "subjectId")
	private List<Subject> listOfSubject;
	
	@ManyToMany
//	@JoinColumn(name = "userId")
	private List<User> listOfUsers;
	
	@OneToMany(mappedBy = "academicPrograms")
	private List<ClassHour> listOfClassHours;

	

}
