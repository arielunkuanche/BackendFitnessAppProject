package springboot.dailyfitness.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import springboot.dailyfitness.domain.FitnessRecord;
import springboot.dailyfitness.repository.AreaRepository;
import springboot.dailyfitness.repository.FitnessRecordRepository;

@Controller
public class DailyFitnessController {
    @Autowired
    private FitnessRecordRepository frRepository;
    @Autowired
    private AreaRepository aRepository;

    // display lists of records
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listRecords", frRepository.findAll());
        return "index";
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
    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable("id")Long id){
        frRepository.deleteById(id);
        return "redirect:/";
    }
}
