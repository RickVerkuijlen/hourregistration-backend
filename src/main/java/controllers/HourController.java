package controllers;

import objects.HourDTO;
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
    HttpEntity<Boolean> saveHour(@RequestBody HourDTO hourDTO) {
        System.out.println("Worked hours: " + hourDTO.getWorkedHours());
        boolean hourSaveSuccess = hourRepository.saveHour(hourDTO);

        if(hourSaveSuccess) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/{month}/{year}")
    public @ResponseBody
    HttpEntity<List<HourDTO>> getMonthOverview(@PathVariable("month") String month,
                                                   @PathVariable("year") String year) {
        List<HourDTO> hourDTOS = hourRepository.getAllHoursFromMonth(month, year);

        if(hourDTOS != null || !hourDTOS.isEmpty()) {
            return new ResponseEntity<>(hourDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
