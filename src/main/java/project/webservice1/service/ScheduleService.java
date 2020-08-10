package project.webservice1.service;

import org.springframework.stereotype.Service;
import project.webservice1.model.Doctor;
import project.webservice1.model.Schedule;
import project.webservice1.repository.DoctorRepo;
import project.webservice1.repository.ScheduleRepo;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ScheduleService implements ScheduleServiceI{

    private ScheduleRepo scheduleRepo;
    private DoctorRepo doctorRepo;

    public ScheduleService(ScheduleRepo scheduleRepo, DoctorRepo doctorRepo) {
        this.scheduleRepo = scheduleRepo;
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Set<Schedule> getAllSchedule() {
        Set<Schedule> schedules = new HashSet<>();
        for(Schedule schedule: this.scheduleRepo.findAll()){
            schedules.add(schedule);
        }
        return schedules;
    }

    @Override
    public Iterable<Schedule> findByDay(DayOfWeek dayOfWeek) {
        return this.scheduleRepo.findAllByDayOfWeek(dayOfWeek);
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return this.scheduleRepo.findById(id);
    }

    @Override
    public Iterable<Schedule> findByDoctor(String doctorName) {
        Optional<Doctor> doctorOptional = this.doctorRepo.findByName(doctorName);
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            return doctor.getSchedules();
        }
        return null;
    }

    @Override
    public Set<Schedule> findAll() {
        Set<Schedule> schedules = new HashSet<>();
        for(Schedule schedule: this.scheduleRepo.findAll()){
            schedules.add(schedule);
        }
        return schedules;
    }

    @Override
    public Schedule save(Schedule schedule) {
        return this.scheduleRepo.save(schedule);
    }

    @Override
    public Optional<Schedule> saveSchedule(Schedule schedule, String doctorName) {
        Optional<Doctor> doctorOptional = this.doctorRepo.findByName(doctorName);
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();

            doctor.addSchedule(schedule);
            doctorRepo.save(doctor);
            return Optional.of(schedule);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Schedule> removeSchedule(Long id) {
        Optional<Schedule> scheduleOptional = this.scheduleRepo.findById(id);
        if (scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();

            scheduleRepo.delete(schedule);
            return scheduleRepo.findById(id);
        }
        return Optional.empty();
    }
}
