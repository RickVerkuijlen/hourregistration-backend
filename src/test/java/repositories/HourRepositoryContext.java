package repositories;

import context.MySQLHourContext;
import net.bytebuddy.utility.RandomString;
import objects.Hour;
import objects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class HourRepositoryContext {
    @InjectMocks
    HourRepository hourRepository;

    @Mock
    MySQLHourContext hourContext;

    @Mock
    UserRepository userRepository;

    private List<Hour> hours;
    private List<User> users;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.hours = new ArrayList<>();
        this.users = new ArrayList<>();
        for(int i = 1; i < 4; i++) {
            hours.add(Hour.builder().projectId(RandomString.make(5)).userId(1).workedHours(i).build());
            users.add(User.builder().id(i).build());
        }

        when(userRepository.allUsers()).thenReturn(this.users);
    }

    @Test
    void getAllHoursFromMonth_equals() {
        when(hourContext.getAllFromMonth(anyInt(), anyInt())).thenReturn(this.hours);

        int expected = 3;
        int actual = hourRepository.getAllHoursFromMonth("1", "2021").size();

        assertEquals(expected, actual);
    }

    @Test
    void getAllHoursFromWeek_equals() {
        when(hourContext.getAllFromWeek(any(Date.class), any(Date.class))).thenReturn(this.hours);

        int expected = 3;
        int actual = hourRepository.getAllHoursFromWeek("1", "2021").size();

        assertEquals(expected, actual);
    }

}
