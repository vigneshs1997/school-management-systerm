package com.school.sba.requestdto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequest {
	private LocalTime opensAt; //09:00:00
	private LocalTime closesAt; //04:30:00
	private int classHoursPerDay;//6
	private int classHourLengthInMinutes;//45
	private LocalTime breakTime; //10:30:00
	private int breakLengthInMinutes;//30
	private LocalTime lunchTime;//14:30:00
	private int lunchLengthInMinutes;//30
}
