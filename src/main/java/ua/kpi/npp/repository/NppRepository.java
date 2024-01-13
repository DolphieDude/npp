package ua.kpi.npp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.npp.entity.Npp;

@Repository
public interface NppRepository extends JpaRepository<Npp, Long> {
}