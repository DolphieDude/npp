package ua.kpi.npp.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.kpi.npp.entity.Employee;
import ua.kpi.npp.entity.Npp;
import ua.kpi.npp.entity.Role;
import ua.kpi.npp.entity.User;
import ua.kpi.npp.repository.EmployeeRepository;
import ua.kpi.npp.repository.NppRepository;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class NppService {

    NppRepository nppRepository;

    EmployeeRepository employeeRepository;

    public Npp startAnNpp(Role role, Employee employee) {
        Npp npp = new Npp(employee, role);
        employee.setNpp(npp);

        employeeRepository.save(employee);
        nppRepository.save(npp);
        return npp;
    }

    public Npp startAnNpp(Role role, Set<Employee> employees) {
        Npp npp = new Npp(employees, role);
        for (Employee employee : employees) {
            employee.setNpp(npp);
        }

        employeeRepository.saveAll(employees);
        return nppRepository.save(npp);
    }

    public Set<User> filterBySubstringInCV(Npp npp, String requiredSubstring) {
        Set<User> users = npp.getUsers();
        Set<User> filteredUsers = new HashSet<>();

        for (User user: users) {
            if (user.getCv().contains(requiredSubstring)) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }
}
