package com.lms.controllers;

import com.lms.models.Role;
import com.lms.models.Status;
import com.lms.repository.RoleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired

	RoleRepository roleRepository;

	static final Logger logger = Logger.getLogger(RoleController.class);

	@PostMapping("/create")
	public Role create(@RequestBody Map<String, String> body){
		String name = body.get("name");


		return roleRepository.save(new Role(name));
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Role getRole(@PathVariable("id") String id) {
		Role role = null;
			int roleId = Integer.parseInt(id);
			role=roleRepository.findById(roleId);


		return role;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Role> getRole() {

		return roleRepository.findAll();
	}


	@DeleteMapping("/{id}")
	public Status delete(@PathVariable String id){
		int roleId = Integer.parseInt(id);

		try {
			roleRepository.delete(roleId);
			return new Status(1, "role deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}

}
