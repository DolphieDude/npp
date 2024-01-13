package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.npp.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}