package springboot.dailyfitness.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.dailyfitness.domain.FitnessUser;

@Repository
public interface FitnessUserRepository extends JpaRepository<FitnessUser, Long>{
    FitnessUser findByUserName(String username);
    boolean existsByUserName(String username);
}
