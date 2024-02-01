package com.school.sba.serviceImpl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.Schedule;
import com.school.sba.entity.School;
import com.school.sba.exception.ScheduleAlreadyPresentException;
import com.school.sba.exception.ScheduleNotFoundException;
import com.school.sba.exception.SchoolNotFoundByIdException;
import com.school.sba.repository.ScheduleRepository;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.requestdto.ScheduleRequest;
import com.school.sba.responsedto.ScheduleResponse;
import com.school.sba.service.ScheduleService;
import com.school.sba.util.ResponseEntityProxy;
import com.school.sba.util.ResponseStructure;

@Service
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private ResponseStructure<ScheduleResponse> responseStructure;


	private ScheduleResponse mapToScheduleResponse(Schedule schedule) {		
		return ScheduleResponse.builder()
				.scheduleId(schedule.getScheduleId())
				.opensAt(schedule.getOpensAt())
				.closesAt(schedule.getClosesAt())
				.classHoursPerDay(schedule.getClassHoursPerDay())
				.classHoursLengthInMinutes((int)
						(Duration.ofMinutes(schedule.getClassHoursLengthInMinutes().toMinutes())
								.toMinutes()))
				.breakTime(schedule.getBreakTime())
				.breakLengthInMinutes(((int)
						(Duration.ofMinutes(schedule.getBreakLengthInMinutes().toMinutes())
								.toMinutes())))
				.lunchLengthInMinutes((int)
						(Duration.ofMinutes(schedule.getLunchLengthInMinutes().toMinutes())
								.toMinutes()))
				.lunchTime(schedule.getLunchTime())
				.build();
	}

	private Schedule mapToSchedule(ScheduleRequest scheduleRequest) {
		return Schedule.builder()
				.opensAt(scheduleRequest.getOpensAt())
				.closesAt(scheduleRequest.getClosesAt())
				.classHoursPerDay(scheduleRequest.getClassHoursPerDay())
				.classHoursLengthInMinutes(Duration.ofMinutes(scheduleRequest.getClassHourLengthInMinutes()))
				.breakTime(scheduleRequest.getBreakTime())
				.breakLengthInMinutes(Duration.ofMinutes(scheduleRequest.getBreakLengthInMinutes()))
				.lunchLengthInMinutes(Duration.ofMinutes(scheduleRequest.getLunchLengthInMinutes()))
				.lunchTime(scheduleRequest.getLunchTime())
				.build();
	}

	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> saveSchedule(int schoolId,
			ScheduleRequest scheduleRequest) {

		return schoolRepository.findById(schoolId)
				.map(school -> {
					if(school.getSchedule() == null) {
						Schedule schedule = scheduleRepository.save(mapToSchedule(scheduleRequest));

						school.setSchedule(schedule);

						schoolRepository.save(school);
						
						return ResponseEntityProxy.setResponseStructure(HttpStatus.CREATED,
								"schedule added successfully",
								mapToScheduleResponse(schedule));
					}
					else {
						throw new ScheduleAlreadyPresentException("Schedule is already added");
					}
				})
				.orElseThrow(() -> new SchoolNotFoundByIdException("school not found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(int schoolId) {

		School school = schoolRepository.findById(schoolId)
				.orElseThrow(() -> new SchoolNotFoundByIdException("School not found"));

		return scheduleRepository.findById(school.getSchedule().getScheduleId())
				.map(schedule -> {
					
					return ResponseEntityProxy.setResponseStructure(HttpStatus.FOUND,
							"schedule found",
							mapToScheduleResponse(schedule));
				})
				.orElseThrow(() -> new ScheduleNotFoundException("schedule not found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> updateSchedule(int scheduleId,
			ScheduleRequest scheduleRequest) {

		return scheduleRepository.findById(scheduleId)
				.map(schedule -> {
					Schedule mapToSchedule = mapToSchedule(scheduleRequest);
					mapToSchedule.setScheduleId(scheduleId);
					schedule = scheduleRepository.save(mapToSchedule);

					return ResponseEntityProxy.setResponseStructure(HttpStatus.OK,
							"schedule updated successfully",
							mapToScheduleResponse(schedule));
				})
				.orElseThrow(() -> new ScheduleNotFoundException("schedule not found"));

	}
	
/*=============================================deleteSchedule(UI AND DATA BASE)==================================================*/
	public ResponseEntity<ResponseStructure<ScheduleResponse>>deleteSchedule(Schedule schedule)
	{                             //fetching the Id
		int scheduleId = schedule.getScheduleId();

		Schedule schedule1 = scheduleRepository.findById(scheduleId)
				.orElseThrow(()-> new ScheduleNotFoundException("schedule not found"));

		scheduleRepository.delete(schedule1);

		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("schedule successfully deleted");
		responseStructure.setData(mapToScheduleResponse(schedule1));

		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure,HttpStatus.OK);
	}


}
