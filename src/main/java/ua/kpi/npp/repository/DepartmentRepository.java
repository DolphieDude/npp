package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.npp.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}