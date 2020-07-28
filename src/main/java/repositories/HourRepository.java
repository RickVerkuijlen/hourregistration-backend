package repositories;

import context.MySQLHourContext;
import objects.HourDTO;
import objects.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class HourRepository {

    @Autowired
    private MySQLHourContext hourContext;

    @Autowired
    private UserRepository userRepository;

    public Boolean saveHour(HourDTO entity) {
        float workedHour = entity.getWorkedHours();

        entity.setWorkedHours(calculateHours(workedHour));

        System.out.println("Worked hours: " + entity.getWorkedHours());

        return hourContext.create(entity) == 1;
    }

    public Boolean updateHour(HourDTO entity) {
        return hourContext.update(entity);
    }

    private float calculateHours(float workedHour) {

        System.out.println("WorkedHour: " + workedHour);
        String floatAsString = String.valueOf(workedHour);

        float totalHour = Float.parseFloat(floatAsString.substring(0, floatAsString.indexOf(".")));
        float behindDecimalPoint = Float.parseFloat(floatAsString.substring(floatAsString.indexOf(".")));

        if(behindDecimalPoint < 0.37f) {
            totalHour += 0.25f;
        } else if(behindDecimalPoint < 0.63f) {
            totalHour += 0.5f;
        } else if (behindDecimalPoint < 0.87f) {
            totalHour += 0.75f;
        } else {
            totalHour += 1;
        }

        System.out.println(totalHour);

        return totalHour;
    }

    public List<HourDTO> getAllHoursFromMonth(String month, String year) {
        List<HourDTO> hours = hourContext.getAllFromMonth(Integer.parseInt(month), Integer.parseInt(year));

        List<UserDTO> users = userRepository.allUsers();

        List<HourDTO> result = new ArrayList<>();

        hours.sort(Comparator.comparing(HourDTO::getProjectId));

        users.forEach(user -> {
            String projectCode = "";
            float totalHoursOnProject = 0;

            HourDTO dto = null;
            List<HourDTO> userHours = hours.stream().filter(o -> o.getUserId() == user.getId()).collect(Collectors.toList());
            for (HourDTO hour : userHours) {

                if(!projectCode.equals(hour.getProjectId()) || projectCode.isEmpty()) {
                    projectCode = hour.getProjectId();
                    dto = new HourDTO();
                    dto.setUserId(user.getId());
                    totalHoursOnProject = 0;
                }

                totalHoursOnProject += hour.getWorkedHours();

                dto.setProjectId(projectCode);

                dto.setWorkedHours(totalHoursOnProject);
                result.remove(dto);
                result.add(dto);
            }
        });
        
        return result;
    }

    public List<HourDTO> getAllHoursFromWeek(String week, String year) {
        List<HourDTO> allHours = hourContext.getAll();

        List<HourDTO> result = new ArrayList<>();

        int weekNumber = Integer.parseInt(week);
        int yearNumber = Integer.parseInt(year);

        Calendar calendar = Calendar.getInstance();

        for (HourDTO hour : allHours) {
            calendar.setTime(hour.getDate());
            int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
            int hourYear = calendar.get(Calendar.YEAR);
            System.out.println(hour.getDate() + ": " + weekOfYear);

            if(weekOfYear == weekNumber && hourYear == yearNumber) {
                result.add(hour);
            }
        }

        return result;
    }
}
