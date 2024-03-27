package springboot.dailyfitness.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import springboot.dailyfitness.domain.FitnessRecord;

@Service
public interface FitnessRecordService {
    
    @EntityGraph(attributePaths = "area")
    Page<FitnessRecord> findPaginated(int pageNo, int pageSize);

}
