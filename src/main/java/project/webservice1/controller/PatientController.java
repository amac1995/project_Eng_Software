package project.webservice1.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.webservice1.model.Patient;
import project.webservice1.service.PatientService;

import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Patient> getAllPatient() {
        return patientService.getAllPatients();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Patient getById(@PathVariable("id") Long id) {
        return patientService.findById(id).get();
    }

    @RequestMapping(value = "/patientID/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Patient getById(@PathVariable("patientID") Integer patientID) {
        return patientService.findByPatientID(patientID).get();
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }


    @PostMapping(value = "/remove/{patientID}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> removePatient(@PathVariable("patientID") Integer patientID) {
        Optional<Patient> patientOptional = patientService.removePatient(patientID);
        if (patientOptional.isPresent()) {
            return ResponseEntity.ok(patientOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
