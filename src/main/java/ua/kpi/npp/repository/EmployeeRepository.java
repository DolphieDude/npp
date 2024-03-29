package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.npp.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}