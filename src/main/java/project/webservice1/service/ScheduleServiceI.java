package project.webservice1.service;

import project.webservice1.model.Schedule;

import java.time.DayOfWeek;
import java.util.Optional;
import java.util.Set;

public interface ScheduleServiceI {

    Set<Schedule> getAllSchedule();

    Iterable<Schedule> findByDay(DayOfWeek dayOfWeek);

    Optional<Schedule> findById(Long id);

    Iterable<Schedule> findByDoctor(String doctorName);

    Set<Schedule> findAll();

    Schedule save(Schedule schedule);

    Optional<Schedule> saveSchedule(Schedule schedule, String doctorName);

    Optional<Schedule> removeSchedule (Long id);
}
