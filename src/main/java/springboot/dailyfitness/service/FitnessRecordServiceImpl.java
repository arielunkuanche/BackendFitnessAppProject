package springboot.dailyfitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import springboot.dailyfitness.domain.FitnessRecord;
import springboot.dailyfitness.repository.FitnessRecordRepository;

@Component
public class FitnessRecordServiceImpl implements FitnessRecordService{
    @Autowired
    public FitnessRecordRepository frRepository;

    @Override
    public org.springframework.data.domain.Page<FitnessRecord> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return this.frRepository.findAll(pageable);

    }

}
