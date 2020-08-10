package project.webservice1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.webservice1.model.Schedule;

import java.time.DayOfWeek;

@Repository
public interface ScheduleRepo extends CrudRepository<Schedule, Long> {
    Iterable<Schedule> findAllByDayOfWeek(DayOfWeek dayOfWeek);

}
