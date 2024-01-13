package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.npp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}