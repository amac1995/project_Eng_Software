package project.webservice1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.webservice1.model.Appointment;
import project.webservice1.service.AppointmentService;
import project.webservice1.service.filter.appointmentFilter.AppointmentObjectFilter;

import java.util.Optional;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    public AppointmentService appointmentService;
    private Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Appointment> getAllAppointment(@ModelAttribute AppointmentObjectFilter appointmentObjectFilter) {
        logger.info(appointmentObjectFilter.toString());
        return appointmentService.getFilteredAppointment(appointmentObjectFilter);
    }

    @PostMapping(value = "/{patientID}/{doctorID}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment, @PathVariable("patientID") Integer patientID, @PathVariable("doctorName") String doctorName) {
        logger.info(appointment.toString() + " " + patientID);
        Optional<Appointment> appointmentOptional = appointmentService.saveAppointment(appointment, patientID, doctorName);
        if (appointmentOptional.isPresent()) {
            return ResponseEntity.ok(appointmentOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping(value = "/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> removeAppointment(@PathVariable("id") Long id) {
        Optional<Appointment> appointmentOptional = appointmentService.deleteAppointment(id);
        if (appointmentOptional.isPresent()) {
            return ResponseEntity.ok(appointmentOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
