package ua.kpi.npp.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ua.kpi.npp.entity.Npp;
import ua.kpi.npp.entity.Role;
import ua.kpi.npp.entity.User;
import ua.kpi.npp.repository.NppRepository;
import ua.kpi.npp.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    NppRepository nppRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void shouldReturnNewUser() {
        Role role = new Role("testRole");
        User user = new User();
        user.setRole(role);
        Npp npp = new Npp();
        npp.setRole(role);

        User expected = new User();
        expected.setRole(role);
        expected.setNpp(npp);

        when(nppRepository.save(any())).thenReturn(null);
        when(userRepository.save(expected)).thenReturn(expected);
        User actual = userService.applyForAJob(user, npp);

        assertEquals(expected, actual);
        verify(userRepository, times(1)).save(expected);
    }


}
