package ua.kpi.npp.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ua.kpi.npp.entity.Employee;
import ua.kpi.npp.entity.Npp;
import ua.kpi.npp.entity.Role;
import ua.kpi.npp.entity.User;
import ua.kpi.npp.repository.EmployeeRepository;
import ua.kpi.npp.repository.NppRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NppServiceTest {
    @Mock
    NppRepository nppRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    NppService nppService;

    @Test
    public void shouldReturnNewNpp() {
        Role role = new Role("testRole");
        Employee employee = new Employee("testEmployee");

        Npp expected = new Npp();
        expected.setRole(role);
        expected.addEmployee(employee);

        when(employeeRepository.save((any()))).thenReturn(null);
        when(nppRepository.save(expected)).thenReturn(expected);
        Npp actual = nppService.startAnNpp(role, employee);

        assertEquals(expected, actual);
        verify(nppRepository, times(1)).save(expected);
    }

    @Test
    public void shouldReturnFilteredUsers() {
        User user1 = new User();
        user1.setCv("footestbar");
        User user2 = new User();
        user2.setCv("foobar");

        Npp npp = new Npp(user1);
        npp.addUser(user2);

        Set<User> expected = new HashSet<>();
        expected.add(user1);

        Set<User> actual = nppService.filterBySubstringInCV(npp, "test");

        assertEquals(expected, actual);
    }
}
