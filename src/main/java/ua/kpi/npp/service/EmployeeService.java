package ua.kpi.npp.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.kpi.npp.entity.Employee;
import ua.kpi.npp.entity.Npp;
import ua.kpi.npp.entity.User;
import ua.kpi.npp.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.Set;

@Transactional
@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public HashMap<User, String> sendMeetingLinkToUsers(Set<User> users) {
        HashMap<User, String> links = new HashMap<>();

        for (User user: users) {
            links.put(user, "meet.google.com/" + user.getUsername());
            System.out.println("Sending a meeting invite email to " + user.getEmail());
        }

        return links;
    }

    public Employee sayYesToCandidate(Npp npp, User user) {
        System.out.println("Sending job-invite email to " + user.getEmail());
        Employee newEmployee = new Employee(user);

        return employeeRepository.save(newEmployee);
    }
}
