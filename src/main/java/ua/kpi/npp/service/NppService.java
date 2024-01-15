package ua.kpi.npp.service;

import org.springframework.stereotype.Service;
import ua.kpi.npp.entity.Employee;
import ua.kpi.npp.entity.Npp;
import ua.kpi.npp.entity.Role;

import java.util.Set;

@Service
public class NppService {

    public void startAnNpp(Role role, Employee employee) {
        Npp npp = new Npp(employee, role);
    }

    public void startAnNpp(Role role, Set<Employee> employees) {
        Npp npp = new Npp(employees, role);
    }
}
