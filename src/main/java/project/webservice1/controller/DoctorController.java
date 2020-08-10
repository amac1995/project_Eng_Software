package project.webservice1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.webservice1.model.Doctor;
import project.webservice1.service.DoctorService;
import project.webservice1.service.DoctorServiceI;
import project.webservice1.service.filter.doctorFilter.DoctorFilterObject;

import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    private DoctorService doctorService;
    private Logger logger = LoggerFactory.getLogger(DoctorController.class);

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Doctor> getAllDoctor(@ModelAttribute DoctorFilterObject doctorFilterObject) {
        logger.info(doctorFilterObject.toString());
        return doctorService.getFilteredDoctors(doctorFilterObject);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Doctor getById(@PathVariable("id") Long id) {
        Optional<Doctor> doctorOptional = doctorService.findById(id);
        return doctorOptional.orElse(null);
    }


    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Doctor getByName(@PathVariable("name") String name) {
        return doctorService.findByName(name).get();
    }


    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }


    @PostMapping(value = "/remove/{doctorName}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> removeDoctor(@PathVariable("doctorName") String doctorName) {
        Optional<Doctor> doctorOptional = doctorService.removeDoctor(doctorName);
        if (doctorOptional.isPresent()) {
            return ResponseEntity.ok(doctorOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
