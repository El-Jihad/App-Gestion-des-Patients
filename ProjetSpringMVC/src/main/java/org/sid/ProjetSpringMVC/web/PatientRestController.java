package org.sid.ProjetSpringMVC.web;



import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientRestController {
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping(path="/listPatients")
	public List<Patient> list()
	{
		return patientRepository.findAll();
	}
	
	@GetMapping(path="/patients/{id}")
	public Patient getOne(@PathVariable Long id)
	{
		return patientRepository.findById(id).get();
	}

}
