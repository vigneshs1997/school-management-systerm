package com.school.sba.service;

import org.springframework.http.ResponseEntity;

import com.school.sba.requestdto.SchoolRequest;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.util.ResponseStructure;

public interface SchoolService {

	ResponseEntity<ResponseStructure<SchoolResponse>> createSchool(SchoolRequest schoolRequest);
	
	// ResponseEntity<ResponseStructure<SchoolResponse>> softDeleteSchool(int schoolId);

	ResponseEntity<ResponseStructure<SchoolResponse>> updateSchool(Integer schoolId, SchoolRequest schoolRequest);

//	ResponseEntity<ResponseStructure<SchoolResponse>> findSchool(Integer schoolId);

}
