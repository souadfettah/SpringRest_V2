package com.lms.controllers;

import com.lms.models.Employee;
import com.lms.models.Projet;
import com.lms.models.Service;
import com.lms.models.Tache;
import com.lms.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/projet")
public class ProjetController {

	@Autowired

    ProjetRepository projetRepository;
	@Autowired

    ServiceRepository serviceRepository;
	@Autowired
    TacheRepository tacheRepository;
	@Autowired
    TacheEmployeeRepository tacheEmployeeRepository;

	@Autowired
    CommentaireRepository commentaireRepository;

	static final Logger logger = Logger.getLogger(ProjetController.class);

	/*@PostMapping("/create")
	public Projet create(@RequestBody Map<String, String> body){
		String nom = body.get("nom");
		String description = body.get("description");
		String date_creation = body.get("date_creation");
		String date_debut = body.get("date_debut");
		String date_fin = body.get("date_fin");
		String budget = body.get("budget");

		String service1 = body.get("service");
		int serviceId = Integer.parseInt(service1);
		Service service = serviceRepository.findById(serviceId);

		return projetRepository.save(new Projet(nom,description,date_creation,date_debut,date_fin,budget,service));
	}*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Projet getProjet(@PathVariable("id") long id) {
		Projet employee = null;
		try {


		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	@RequestMapping(value= "/employees/{idprojet}")
	public @ResponseBody
	List<Employee> getEmployees(@PathVariable("idprojet") long idprojet ){
		List<Employee> employees = null ; 
		try{

		}catch(Exception e){
			e.printStackTrace();
		}
		return employees; 
	}

	@RequestMapping(value = "/taches/{idproject}", method = RequestMethod.GET)
	public @ResponseBody
	List<Tache> getTache(@PathVariable("id") String id ) {
		return tacheRepository.ProjetTache(id);
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Projet> getProjet() {

		return projetRepository.findAll();
	}
}
