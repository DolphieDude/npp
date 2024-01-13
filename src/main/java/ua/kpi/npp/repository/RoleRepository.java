package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.npp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}