package ua.kpi.npp.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ua.kpi.npp.entity.Employee;
import ua.kpi.npp.entity.Npp;
import ua.kpi.npp.entity.User;
import ua.kpi.npp.repository.EmployeeRepository;
import ua.kpi.npp.repository.NppRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    NppRepository nppRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void shouldReturnMapWithLinks() {
        Set<User> users = new HashSet<>();

        User user1 = new User("user1", "test1@mail.com");
        User user2 = new User("user2", "test2@mail.com");

        users.add(user1);
        users.add(user2);

        Map<User, String> expected = new HashMap<>();
        expected.put(user1, "meet.google.com/user1");
        expected.put(user2, "meet.google.com/user2");

        Map<User, String> actual = employeeService.sendMeetingLinkToUsers(users);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateNewEmployeeAndSaveIt() {
        User user = new User("user1", "test1@mail.com");
        Npp npp = new Npp(user);

        Employee expected = new Employee(user);

        when(employeeRepository.save(expected)).thenReturn(expected);
        when(nppRepository.save(any())).thenReturn(null);
        Employee actual = employeeService.sayYesToCandidate(npp, user);

        assertEquals(expected, actual);
        verify(employeeRepository, times(1)).save(expected);
    }
}
