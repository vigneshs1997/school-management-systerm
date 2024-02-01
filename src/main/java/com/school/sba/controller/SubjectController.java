package com.school.sba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.requestdto.SubjectRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.responsedto.SubjectResponse;
import com.school.sba.service.SubjectService;
import com.school.sba.util.ResponseStructure;

@RestController
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/academic-programs/{programId}/subjects")
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addSubject(@PathVariable("programId") int programId,
			@RequestBody SubjectRequest subjectRequest){
		return subjectService.addSubject(programId, subjectRequest);
	}
	
	
	@GetMapping("/subjects")
	public ResponseEntity<ResponseStructure<List<SubjectResponse>>> findAllSubjects(){
		return subjectService.findAllSubjects();
	}

}
