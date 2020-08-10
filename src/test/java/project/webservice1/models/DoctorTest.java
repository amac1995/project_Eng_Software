package project.webservice1.models;

import org.junit.Test;
import project.webservice1.model.Appointment;
import project.webservice1.model.Doctor;
import project.webservice1.model.Schedule;
import project.webservice1.model.Specialty;
import project.webservice1.repository.DoctorRepo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class DoctorTest {
    @Test
    public void testAddConsultaToMedico() {

        DoctorRepo doctorRepo;

        Specialty oftomologista = new Specialty("Oftomologista", 25);

        Doctor doctor = new Doctor("Rui", "Ovar", 35, "rui@clinic.pt", 1, oftomologista);

        Set<Schedule> schedules = new HashSet<>();
        Schedule schedule = new Schedule();
        schedule.setDayOfWeek(DayOfWeek.MONDAY);
        schedule.setStartHour(LocalTime.of(15, 30));
        schedule.setEndHour(LocalTime.of(18, 0));
        schedules.add(schedule);
        Schedule schedule1 = new Schedule();
        schedule1.setDayOfWeek(DayOfWeek.FRIDAY);
        schedule1.setStartHour(LocalTime.of(10, 0));
        schedule1.setEndHour(LocalTime.of(12, 0));
        schedules.add(schedule1);
        doctor.setSchedules(schedules);

        Appointment appointment = new Appointment();
        appointment.setSchedule(LocalDateTime.of(2019, 1, 7, 16, 0));

        Appointment appointment1 = new Appointment();
        appointment1.setSchedule(LocalDateTime.of(2019, 1, 7, 16, 45));

        Appointment appointment2 = new Appointment();
        appointment2.setSchedule(LocalDateTime.of(2019, 1, 11, 11, 0));

        assertEquals("First test", 0, doctor.getAppointments().size());
        doctor.addAppointmentDoctor(appointment);
        assertEquals("Add 1º appointment",1, doctor.getAppointments().size());
        doctor.addAppointmentDoctor(appointment1);
        assertEquals("Add 2º appointment",1, doctor.getAppointments().size());
        doctor.addAppointmentDoctor(appointment2);
        assertEquals("Add 3º appointment",2, doctor.getAppointments().size());
    }
}
