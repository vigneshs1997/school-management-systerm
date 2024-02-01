package com.school.sba.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor//It works with the arguments
public class AcademicProgramNotAssignedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String message;//for which,we need to create constructor
}
