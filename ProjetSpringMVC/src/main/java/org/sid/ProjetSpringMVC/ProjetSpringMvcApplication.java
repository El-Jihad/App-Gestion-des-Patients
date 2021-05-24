package org.sid.ProjetSpringMVC;

import java.util.Date;

import org.sid.ProjetSpringMVC.dao.PatientRepository;
import org.sid.ProjetSpringMVC.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetSpringMvcApplication implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*patientRepository.save(new Patient(null,"Hassan",5,new Date(),false));
		patientRepository.save(new Patient(null,"Mohammed",4,new Date(),true));
		patientRepository.save(new Patient(null,"Fatima",6,new Date(),false));
		patientRepository.save(new Patient(null,"Saida",7,new Date(),true));
		patientRepository.save(new Patient(null,"Karim",12,new Date(),false));
		patientRepository.save(new Patient(null,"Fouad",9,new Date(),true));
		patientRepository.save(new Patient(null,"Yasser",8,new Date(),true));
		patientRepository.save(new Patient(null,"Yassine",11,new Date(),false));
		patientRepository.save(new Patient(null,"Adam",10,new Date(),false));*/
		
		patientRepository.findAll().forEach(p->{
			System.out.println(p.getName());
		});
		
		
	}

}
