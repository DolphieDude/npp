package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.npp.entity.Npp;

public interface NppRepository extends JpaRepository<Npp, Long> {
}