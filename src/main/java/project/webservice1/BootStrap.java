package project.webservice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import project.webservice1.model.*;
import project.webservice1.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AppointmentRepo appointmentRepo;

    private EmployeeRepo employeeRepo;

    private DoctorRepo doctorRepo;

    private PatientRepo patientRepo;

    private SpecialtyRepo specialtyRepo;

    private ScheduleRepo scheduleRepo;

    public BootStrap(AppointmentRepo appointmentRepo, EmployeeRepo employeeRepo, DoctorRepo doctorRepo, PatientRepo patientRepo, SpecialtyRepo specialtyRepo, ScheduleRepo scheduleRepo) {
        this.appointmentRepo = appointmentRepo;
        this.employeeRepo = employeeRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.specialtyRepo = specialtyRepo;
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){

        Specialty geral = new Specialty();
        geral.setName("Geral");
        geral.setCost(20);
        Specialty cirurgiao = new Specialty();
        cirurgiao.setName("Cirurgião");
        cirurgiao.setCost(50);
        Specialty to = new Specialty("Terapeuta Ocupacional", 40);
        to.setName("Terapeuta Ocupacional");
        to.setCost(40);
        specialtyRepo.save(geral);
        specialtyRepo.save(cirurgiao);
        specialtyRepo.save(to);

        Doctor doctor1 = new Doctor("Alexandre", "Ovar", 21, "31202@ufp.edu.pt", 991, cirurgiao);
        Doctor doctor2 = new Doctor("Andre", "Cucujães", 22, "31562@ufp.edu.pt", 992, geral);
        doctorRepo.save(doctor1);
        doctorRepo.save(doctor2);

        Set<Specialty> specialty2=new HashSet<>();
        specialty2.add(cirurgiao);
        Set<Specialty> specialty1=new HashSet<>();
        specialty1.add(geral);
        doctor1.setSpecialties(specialty2);
        doctor1.addSpecialty(cirurgiao);
        doctor2.setSpecialties(specialty1);
        doctor2.addSpecialty(geral);

        Set<Schedule> schedules1 = new HashSet<>();
        Schedule schedule1 = new Schedule();
        schedule1.setDayOfWeek(DayOfWeek.MONDAY);
        schedule1.setStartHour(LocalTime.of(10, 0));
        schedule1.setEndHour(LocalTime.of(15, 0));
        schedules1.add(schedule1);
        Schedule schedule2 = new Schedule();
        schedule2.setDayOfWeek(DayOfWeek.FRIDAY);
        schedule2.setStartHour(LocalTime.of(10, 0));
        schedule2.setEndHour(LocalTime.of(15, 0));
        schedules1.add(schedule2);
        doctor1.setSchedules(schedules1);
        doctor1.addSchedule(schedule1);
        doctor1.addSchedule(schedule2);

        Set<Schedule> schedules2 = new HashSet<>();
        Schedule schedule3 = new Schedule();
        schedule3.setDayOfWeek(DayOfWeek.SATURDAY);
        schedule3.setStartHour(LocalTime.of(8, 30));
        schedule3.setEndHour(LocalTime.of(13, 0));
        schedules2.add(schedule3);
        Schedule schedule4 = new Schedule();
        schedule4.setDayOfWeek(DayOfWeek.THURSDAY);
        schedule4.setStartHour(LocalTime.of(15, 0));
        schedule4.setEndHour(LocalTime.of(19, 0));
        schedules2.add(schedule4);
        doctor2.setSchedules(schedules2);
        doctor2.addSchedule(schedule3);
        doctor2.addSchedule(schedule4);

        Patient patient1 = new Patient("Ana", "Arada", 21, "ana@patients.clinic", 1);
        Patient patient2 = new Patient("João", "Valega", 21, "joao@patients.clinic", 2);
        Patient patient3 = new Patient("Relvas", "Arada", 21, "relvas@patients.clinic", 3);
        Patient patient4 = new Patient("Ricardo", "Valega", 20, "ricardo@patients.clinic", 4);
//        entityManager.persist(patient1);
//        entityManager.persist(patient2);
//        entityManager.persist(patient3);
//        entityManager.persist(patient4);

        patientRepo.save(patient1);
        patientRepo.save(patient2);
        patientRepo.save(patient3);
        patientRepo.save(patient4);

        Set<Appointment> appointments1 = new HashSet<>();
        Set<Appointment> appointments2 = new HashSet<>();
        Set<Appointment> appointments3 = new HashSet<>();
        Appointment appointment1 = new Appointment();
        appointment1.setSchedule(LocalDateTime.of(2019, 1, 7, 11, 0));
        appointment1.setDoctor(doctor1);
        appointment1.setPatient(patient1);
        appointment1.setSpecialty(doctor1.getSpecialty());
        Appointment appointment2 = new Appointment();
        appointment2.setSchedule(LocalDateTime.of(2019, 1, 12, 10, 30));
        appointment2.setDoctor(doctor2);
        appointment2.setPatient(patient2);
        appointment2.setSpecialty(doctor2.getSpecialty());
        Appointment appointment3 = new Appointment();
        appointment3.setSchedule(LocalDateTime.of(2019, 1, 11, 14, 15));
        appointment3.setDoctor(doctor1);
        appointment3.setPatient(patient3);
        appointment3.setSpecialty(doctor1.getSpecialty());

//        entityManager.persist(appointment1);
//        entityManager.persist(appointment2);
//        entityManager.persist(appointment3);

        appointments1.add(appointment1);
        appointments2.add(appointment2);
        appointments3.add(appointment3);

        doctor1.setAppointments(appointments1);
        doctor1.addAppointmentDoctor(appointment1);

        doctor2.setAppointments(appointments2);
        doctor2.addAppointmentDoctor(appointment2);

        doctor1.setAppointments(appointments3);
        doctor1.addAppointmentDoctor(appointment3);


        scheduleRepo.save(schedule1);
        scheduleRepo.save(schedule2);
        scheduleRepo.save(schedule3);
        scheduleRepo.save(schedule4);


        appointmentRepo.save(appointment1);
        appointmentRepo.save(appointment2);
        appointmentRepo.save(appointment3);



//            Set<Doctor> doctors = createDoctorsFromFile("doctors1.txt");
//            //Set<Doctor> doctors = createDoctorsFromFile("doctors2.txt");
//            Set<Patient> patients = createPatientFromFile("patients1.txt");
////            Set<Patient> patients = createPatientFromFile("patients2.txt");
//            Set<Appointment> appointments = createAppointmentsFromFile("appointments1.txt");
//            doctorRepo.saveAll(doctors);
//            patientRepo.saveAll(patients);
//            appointmentRepo.saveAll(appointments);


//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
