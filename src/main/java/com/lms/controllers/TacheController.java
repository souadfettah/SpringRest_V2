package com.lms.controllers;

/*mport com.lms.models.*;
import com.lms.repository.CommentaireRepository;
import com.lms.repository.ProjetRepository;
import com.lms.repository.TacheEmployeeRepository;
import com.lms.repository.TacheRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tache")
public class TacheController {



	@Autowired
    ProjetRepository projetRepository ;
	@Autowired
    TacheRepository tacheRepository;
	@Autowired
    TacheEmployeeRepository tacheEmployeeRepository;

	@Autowired
    CommentaireRepository commentaireRepository;

	static final Logger logger = Logger.getLogger(TacheController.class);

	@RequestMapping(value = "/create/{projetID}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
    Status addTache(@RequestBody Tache tache , @PathVariable("projetID") long id) {
		try {


			return new Status(1, "Tache added Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}
	@PostMapping("/create")
	public Tache create(@RequestBody Map<String, String> body){
	 String nom = body.get("nom");
		 String description =body.get("description");
		 String deadline = body.get("deadline");
	 String priorite = body.get("priorite");

		String projet1 = body.get("projet");
		int projetId = Integer.parseInt(projet1);
		Projet projet = projetRepository.findById(projetId);

		return tacheRepository.save(new Tache(nom,description,deadline,priorite,projet));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Tache getTache(@PathVariable("id") String id) {
		Tache tache = null;
		try {
			int tacheId = Integer.parseInt(id);
			tache=tacheRepository.findById(tacheId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tache;
	}
	@RequestMapping(value = "/commentaires/{id}", method = RequestMethod.GET)
	public @ResponseBody
	List<Commentaire> getTacheCommentaires(@PathVariable("id") String id) {
		List<Commentaire> tache = null;
		try {
			tache=commentaireRepository.commentaireTache(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tache;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Tache> getTache() {

		return tacheRepository.findAll();
	}

	@RequestMapping(value = "/listByEmployee/{empId}", method = RequestMethod.GET)
	public @ResponseBody
	HashSet<Tache> getTacheByEmployee(@PathVariable("empId") String id ) {

		List<TacheEmployee> tacheEmployeesList = null;
		HashSet<Tache> tacheList = new HashSet<Tache>();
		try {
			tacheEmployeesList = tacheEmployeeRepository.EmployeeTache(id);

			int tacheId = 0;

			for(final TacheEmployee i : tacheEmployeesList){


				tacheList.add(tacheRepository.findById(i.getTacheEmployee().getId()));


			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tacheList;
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
	public @ResponseBody
	List<TacheEmployee> getTacheEmployees( @PathVariable("id") String id ) {
		List<TacheEmployee> employeesList = null;
		int tacheId = Integer.parseInt(id);

		employeesList=tacheEmployeeRepository.TacheEmployee(id);

		return employeesList;
	}

	@DeleteMapping("/{id}")
	public Status deleteTache(@PathVariable String id){
		int tacheId = Integer.parseInt(id);

		try {
			tacheRepository.delete(tacheId);
			return new Status(1, "Commentaire deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}
}*/
