package controllers;

import objects.Hour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.HourRepository;

import java.util.List;

@RestController
@RequestMapping("/hours")
@CrossOrigin(origins = "http://localhost:3000")
public class HourController {

    @Autowired
    private HourRepository hourRepository;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public @ResponseBody
    HttpEntity<Boolean> saveHour(@RequestBody Hour hour) {
        boolean hourSaveSuccess = hourRepository.saveHour(hour);

        if(hourSaveSuccess) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public @ResponseBody
    HttpEntity<Boolean> updateHour(@RequestBody Hour hour) {
        boolean hourUpdateSuccess = hourRepository.updateHour(hour);

        if(hourUpdateSuccess) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/month/{month}/{year}")
    public @ResponseBody
    HttpEntity<List<Hour>> getMonthOverview(@PathVariable("month") String month,
                                            @PathVariable("year") String year) {
        List<Hour> hours = hourRepository.getAllHoursFromMonth(month, year);

        if(hours != null && !hours.isEmpty()) {
            return new ResponseEntity<>(hours, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/week/{week}/{year}")
    public @ResponseBody
    HttpEntity<List<Hour>> getWeeklyOverview(@PathVariable("week") String week,
                                             @PathVariable("year") String year) {
        List<Hour> hours = hourRepository.getAllHoursFromWeek(week, year);

        if(hours != null && !hours.isEmpty()) {
            return new ResponseEntity<>(hours, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value="/{projectId}")
    public @ResponseBody
    HttpEntity<List<Hour>> getHourOverviewById(@PathVariable("projectId") String projectId) {
        List<Hour> hours = hourRepository.getAllHoursFromProject(projectId);

        if(hours != null && !hours.isEmpty()) {
            return new ResponseEntity<>(hours, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
