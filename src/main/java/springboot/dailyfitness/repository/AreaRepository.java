package springboot.dailyfitness.repository;
import springboot.dailyfitness.domain.Area;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByAreaName(String areaName);
}
