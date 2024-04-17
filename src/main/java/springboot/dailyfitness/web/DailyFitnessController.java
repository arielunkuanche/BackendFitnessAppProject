package springboot.dailyfitness.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.ui.Model;

import springboot.dailyfitness.domain.FitnessRecord;
import springboot.dailyfitness.repository.AreaRepository;
import springboot.dailyfitness.repository.FitnessRecordRepository;
import springboot.dailyfitness.service.FitnessRecordService;

@Controller
public class DailyFitnessController {
    @Autowired
    private FitnessRecordRepository frRepository;
    @Autowired
    private AreaRepository aRepository;
    @Autowired
    private FitnessRecordService fitnessRecordService;

    // Show all students
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
    // display lists of records
    @GetMapping("/")
    public String viewHomePage(Model model){
        // model.addAttribute("listRecords", frRepository.findAll());
        // return "index";
        return findPaginated(1, "exerciseName", "asc", model);
    }

    // create model attribute to bind form data
    // attribute key name 'fitnessRecord', value: new FitnessRecord()
    @GetMapping("/showAddForm")
    public String addForm(Model model){
        model.addAttribute("fitnessRecord", new FitnessRecord());
        model.addAttribute("areas", aRepository.findAll());
        return "addForm"; 
    }

    //save fitness record to database
    @PostMapping("/save")
    public String save(FitnessRecord fitnessRecord){
        frRepository.save(fitnessRecord);
        return "redirect:/";
    }

    // update record to database by id
    @GetMapping("/update/{id}")
    public String updateRecord(@PathVariable("id")Long id, Model model){
        model.addAttribute("fitnessRecord",frRepository.findById(id));
        model.addAttribute("areas", aRepository.findAll());
        return "updateForm";
    }

    // delete record to database by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable("id")Long id, Model model){
        frRepository.deleteById(id);
        return "redirect:/";
    }

    // get paginated data page
    // sorting example url: /page/1?sortField=exerciseName&sortDir=asc
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo, 
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){
        int pageSize = 5;

        Page<FitnessRecord> page = fitnessRecordService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<FitnessRecord> listRecords = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listRecords", listRecords);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc":"asc");
        return "index";
    }

}
