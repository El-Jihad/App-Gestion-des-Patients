package org.sid.ProjetSpringMVC.web;



import javax.validation.Valid;

import org.sid.ProjetSpringMVC.dao.PatientRepository;
import org.sid.ProjetSpringMVC.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;
	@GetMapping(path="/index")
	public String index()
	{
		return "index";
	}
	
	@GetMapping(path="/AllPatients")
	public String AllPatients(Model model,
			@RequestParam(name="page",defaultValue = "0" ) int page,
			@RequestParam(name="size",defaultValue = "5" ) int size,
			@RequestParam(name="keyword",defaultValue = "" ) String mc)
	{
		//List<Patient> patients= patientRepository.findAll();
		//Page<Patient> pagePatients= patientRepository.findAll(PageRequest.of(page, size));
		Page<Patient> pagePatients= patientRepository.findByNameContains(mc, PageRequest.of(page, size));
		//model.addAttribute("patients", patients);
		model.addAttribute("patients", pagePatients.getContent());
		model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("keyword", mc);
		return "AllPatients";
	}
	
	/*@GetMapping(path="/deletePatient")
	public String delete(Long id,String keyword,int page,int size )
	{
		patientRepository.deleteById(id);
		return "redirect:/AllPatients?/page="+page+"&size="+size+"&keyword="+keyword;
	}*/
	@GetMapping(path="/deletePatient")
	public String delete(Long id,String keyword,int page,int size,Model model)
	{
		patientRepository.deleteById(id);
		return AllPatients(model,page,size,keyword);
	}
	
	@GetMapping(path="/formPatient")
	public String formPatient(Model model)
	{
		model.addAttribute("patient", new Patient());
		model.addAttribute("mode", "new");
		return "formPatient";
	}
	
	@PostMapping(path="/savePatient")
	public String savePatient(Model model,@Valid Patient patient,BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) {
			return "formPatient";
		}
		patientRepository.save(patient);
		model.addAttribute("patient",patient);
		return "confirmation";
	}
	
	@GetMapping(path="/editPatient")
	public String editPatient(Model model,Long id)
	{
		Patient patient= patientRepository.findById(id).get();
		model.addAttribute("mode", "edit");
		model.addAttribute("patient", patient);
		return "formPatient";
	}

}
