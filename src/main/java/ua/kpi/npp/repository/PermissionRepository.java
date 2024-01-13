package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.npp.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}