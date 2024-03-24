package springboot.dailyfitness.repository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import springboot.dailyfitness.domain.FitnessRecord;

@Repository
public interface FitnessRecordRepository extends JpaRepository<FitnessRecord, Long> {
    List<FitnessRecord> findByExerciseName(@Param("exerciseName") String exerciseName);
    List<FitnessRecord> findByDate(@Param("date") LocalDate date);
}
