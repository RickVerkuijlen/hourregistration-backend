package repositories;

import context.MySQLHourContext;
import objects.HourDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HourRepository {

    @Autowired
    private MySQLHourContext hourContext;

    public Boolean saveHour(HourDTO entity) {
        return hourContext.create(entity);
    }
}
