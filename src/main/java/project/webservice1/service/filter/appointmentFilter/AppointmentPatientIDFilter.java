package project.webservice1.service.filter.appointmentFilter;

import project.webservice1.model.Appointment;

import java.util.Set;
import java.util.stream.Collectors;

public class AppointmentPatientIDFilter implements AppointmentFilter {
    private Integer patientID;

    public AppointmentPatientIDFilter(Integer patientID) {
        this.patientID = patientID;
    }

    @Override
    public Set<Appointment> filter(Set<Appointment> appointments) {
        if(patientID==null) return appointments;
        return appointments.stream().filter(appointment -> appointment.getPatient().getPatientID().equals(this.patientID)).collect(Collectors.toSet());
    }
}
