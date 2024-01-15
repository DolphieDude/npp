package ua.kpi.npp.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.kpi.npp.entity.Employee;
import ua.kpi.npp.entity.Npp;
import ua.kpi.npp.entity.User;
import ua.kpi.npp.repository.EmployeeRepository;
import ua.kpi.npp.repository.UserRepository;

@Transactional
@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public Employee sayYesToCandidate(Npp npp, User user) {
        Employee newEmployee = new Employee(user);

        return employeeRepository.save(newEmployee);
    }
}
