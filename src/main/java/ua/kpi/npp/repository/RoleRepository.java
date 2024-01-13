package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.npp.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    // Custom method to find roles by a set of permission names
    List<Role> findByPermissions_NameIn(List<String> permissionNames);
}