package springboot.dailyfitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;

import springboot.dailyfitness.domain.FitnessRecord;
import springboot.dailyfitness.repository.FitnessRecordRepository;

@Component
public class FitnessRecordServiceImpl implements FitnessRecordService{
    @Autowired
    public FitnessRecordRepository frRepository;

    @Override
    public Page<FitnessRecord> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
        return this.frRepository.findAll(pageable);

    }

}
