package springboot.dailyfitness.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import jakarta.validation.*;
import springboot.dailyfitness.domain.FitnessUser;
import springboot.dailyfitness.repository.FitnessUserRepository;

import springboot.dailyfitness.web.dto.AppUserRegistrationDto;

@Controller
public class AppUserRegistrationController {

    @Autowired
    private FitnessUserRepository frepository;

    @RequestMapping(value = "registration")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new AppUserRegistrationDto());
        return "registration";
    }

    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */


    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") AppUserRegistrationDto AppuserRegistrationDto, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) { // validation errors
    		if (AppuserRegistrationDto.getPassword().equals(AppuserRegistrationDto.getPasswordCheck())) { // check password match		
					String pwd = AppuserRegistrationDto.getPassword();
					BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
					String hashPwd = bc.encode(pwd);
		
					FitnessUser newUser = new FitnessUser();
					newUser.setPassword(hashPwd);
					newUser.setUserName(AppuserRegistrationDto.getUsername());
					newUser.setRole("USER");
					if (frepository.findByUserName(AppuserRegistrationDto.getUsername()) == null) { // Check if user exists
						frepository.save(newUser);
					}
					else {
						bindingResult.rejectValue("username", "err.username", "Username already exists");    	
						return "registration";		    		
					}
				} else {
					bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
					return "registration";
			}
		}else {
			return "registration";
		}
        return "redirect:/registration?success";
    }
}
