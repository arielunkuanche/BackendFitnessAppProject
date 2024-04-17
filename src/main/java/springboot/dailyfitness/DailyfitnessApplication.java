package springboot.dailyfitness;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springboot.dailyfitness.domain.Area;
import springboot.dailyfitness.domain.FitnessUser;
import springboot.dailyfitness.repository.AreaRepository;
import springboot.dailyfitness.repository.FitnessUserRepository;

@SpringBootApplication
public class DailyfitnessApplication {
	private static final Logger log = LoggerFactory.getLogger(DailyfitnessApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DailyfitnessApplication.class, args);
	}

	@Bean
	public CommandLineRunner fitnessUserDemo(AreaRepository arepository, FitnessUserRepository frepository){
		return (args) -> {

			// Create training areas;
			log.info("Save list of training area");
			arepository.save(new Area("Back"));
			arepository.save(new Area("Glute"));
			arepository.save(new Area("Shoulder"));
			arepository.save(new Area("Waist"));
			arepository.save(new Area("Core"));
			arepository.save(new Area("Thigh"));
			arepository.save(new Area("Arm"));
			

			// Create users: admin/admin
			// Check if users exist before creating them
			if (!frepository.existsByUserName("admin")) {
				FitnessUser user1 = new FitnessUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
				frepository.save(user1);
			}
			
			
	};
}

}
