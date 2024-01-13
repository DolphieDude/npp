package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.npp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}