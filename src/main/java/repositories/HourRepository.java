package repositories;

import context.MySQLHourContext;
import objects.HourDTO;
import objects.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class HourRepository {

    @Autowired
    private MySQLHourContext hourContext;

    @Autowired
    private UserRepository userRepository;

    public Boolean saveHour(HourDTO entity) {
        return hourContext.create(entity);
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
}
