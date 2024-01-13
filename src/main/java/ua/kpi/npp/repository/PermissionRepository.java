package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.npp.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}