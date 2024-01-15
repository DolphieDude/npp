package ua.kpi.npp.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.kpi.npp.entity.Employee;
import ua.kpi.npp.entity.Npp;
import ua.kpi.npp.entity.User;
import ua.kpi.npp.repository.EmployeeRepository;
import ua.kpi.npp.repository.NppRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Transactional
@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    NppRepository nppRepository;

    public Map<User, String> sendMeetingLinkToUsers(Set<User> users) {
        Map<User, String> links = new HashMap<>();

        for (User user: users) {
            links.put(user, "meet.google.com/" + user.getUsername());
            System.out.println("Sending a meeting invite email to " + user.getEmail());
        }

        return links;
    }

    public Employee sayYesToCandidate(Npp npp, User user) {
        System.out.println("Sending job-invite email to " + user.getEmail());
        npp.removeUser(user);
        Employee newEmployee = new Employee(user);

        nppRepository.save(npp);
        employeeRepository.save(newEmployee);
        return newEmployee;
    }
}
