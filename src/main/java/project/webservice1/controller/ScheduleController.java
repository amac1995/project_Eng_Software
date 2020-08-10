package project.webservice1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.webservice1.model.Schedule;
import project.webservice1.service.ScheduleService;

import java.util.Optional;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;

    private Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Schedule> getAllSchedule() {
        return scheduleService.findAll();
    }

    @RequestMapping(value = "/{doctorName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Schedule> getByDoctor(@PathVariable("doctorName") String doctorName) {
        return scheduleService.findByDoctor(doctorName);
    }


    @PostMapping(value = "/{doctorName}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Schedule> saveWorkTime(@RequestBody Schedule schedule, @PathVariable("doctorName") String doctorName) {
        logger.info(schedule.toString() + " " + doctorName);
        Optional<Schedule> scheduleOptional = scheduleService.saveSchedule(schedule, doctorName);
        if (scheduleOptional.isPresent()) {
            return ResponseEntity.ok(scheduleOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    //remove consuta
    @PostMapping(value = "/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Schedule> removeSchedule(@PathVariable("id") Long id) {
        Optional<Schedule> scheduleOptional = scheduleService.removeSchedule(id);
        if (scheduleOptional.isPresent()) {
            return ResponseEntity.ok(scheduleOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
