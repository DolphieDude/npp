package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.kpi.npp.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    List<Role> findByDescriptionContaining(String keyword);

    List<Role> findByOrderByNameAsc();

    @Query("SELECT r FROM Role r WHERE r.name LIKE %:keyword%")
    List<Role> searchRoles(@Param("keyword") String keyword);

}