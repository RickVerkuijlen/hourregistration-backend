package repositories;

import context.MySQLHourContext;
import objects.Hour;
import objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class HourRepository {

    @Autowired
    private MySQLHourContext hourContext;

    @Autowired
    private UserRepository userRepository;

    public Boolean saveHour(Hour entity) {
        float workedHour = entity.getWorkedHours();

        entity.setWorkedHours(calculateHours(workedHour));

        System.out.println("Worked hours: " + entity.getWorkedHours());

        return hourContext.create(entity) == 1;
    }

    public Boolean updateHour(Hour entity) {
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

    public List<Hour> getAllHoursFromMonth(String month, String year) {
        List<Hour> hours = hourContext.getAllFromMonth(Integer.parseInt(month), Integer.parseInt(year));

        List<User> users = userRepository.allUsers();

        List<Hour> result = new ArrayList<>();

        hours.sort(Comparator.comparing(Hour::getProjectId));

        users.forEach(user -> {
            String projectCode = "";
            float totalHoursOnProject = 0;

            Hour dto = null;
            List<Hour> userHours = hours.stream().filter(o -> o.getUserId() == user.getId()).collect(Collectors.toList());
            for (Hour hour : userHours) {

                if(!projectCode.equals(hour.getProjectId()) || projectCode.isEmpty()) {
                    projectCode = hour.getProjectId();
                    dto = Hour.builder().userId(user.getId()).build();
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

    public List<Hour> getAllHoursFromWeek(String week, String year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(week));
        cal.set(Calendar.DAY_OF_WEEK, 2);
        cal.set(Calendar.YEAR, Integer.parseInt(year));

        Date startDate = cal.getTime();
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date endDate = cal.getTime();

        List<Hour> allHours = hourContext.getAllFromWeek(startDate, endDate);

        return allHours;
    }
}
