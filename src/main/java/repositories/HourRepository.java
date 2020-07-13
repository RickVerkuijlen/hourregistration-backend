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

        List<HourDTO> userHours = new ArrayList<>();

        hours.sort(Comparator.comparing(HourDTO::getProjectId));

//        (float) userHours.stream().mapToDouble(x -> (double) x.getWorkedHours()).sum()
        users.forEach(user -> {
            String projectCode = "";
            float totalHoursOnProject = 0;
            HourDTO dto = new HourDTO();
            dto.setUserId(user.getId());

            for (HourDTO hour : hours) {
                dto.setId(hour.getId());
                if(!projectCode.equals(hour.getProjectId()) || projectCode.isEmpty()) {
                    projectCode = hour.getProjectId();
                    dto = new HourDTO();
                    dto.setUserId(user.getId());
                }

                totalHoursOnProject += hour.getWorkedHours();

                if(hour.getUserId() != user.getId()) {
                    totalHoursOnProject = 0;
                }

                dto.setProjectId(projectCode);
                dto.setWorkedHours(totalHoursOnProject);

                if(!result.contains(dto)) {
                    result.add(dto);
                }

            }
        });


        return result;
    }
}
