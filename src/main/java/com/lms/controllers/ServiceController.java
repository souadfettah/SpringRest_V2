package com.lms.controllers;

import com.lms.models.Projet;
import com.lms.models.Service;
import com.lms.models.Status;
import com.lms.repository.ServiceRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/service")
public class ServiceController {
	@Autowired


	ServiceRepository serviceRepository;

	static final Logger logger = Logger.getLogger(ServiceController.class);

	@PostMapping("/create")
	public Service create(@RequestBody Map<String, String> body){
		String name = body.get("name");


		return serviceRepository.save(new Service(name));
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Service getService(@PathVariable("id") String id) {
		Service service = null;
		int serviceId = Integer.parseInt(id);
		service = serviceRepository.findById(serviceId);
		return service;
	}
	@RequestMapping(value = "/projets/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Set<Projet> getServiceProjets(@PathVariable("id") long id) {
		Set<Projet> service = null;
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return service;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Service> getService() {
		return serviceRepository.findAll();
	}

	@DeleteMapping("/{id}")
	public Status delete(@PathVariable String id){
		int serviceId = Integer.parseInt(id);

		try {
			serviceRepository.delete(serviceId);
			return new Status(1, "role deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}
}
