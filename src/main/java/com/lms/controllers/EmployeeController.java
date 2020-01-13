package com.lms.controllers;

import com.lms.models.*;
import com.lms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
	@RequestMapping("/employee")
public class EmployeeController{


	@Autowired
    EmployeeRepository employeeRepository;

	@Autowired
    TacheEmployeeRepository tacheEmployeeRepository;

	@Autowired
    TacheRepository tacheRepository;

	@Autowired
    RoleRepository roleRepository;

	@Autowired
    ServiceRepository serviceRepository;



	@PostMapping("/create")
	public Employee create(@RequestBody Map<String, String> body){

		String nom = body.get("nom");
		String prenom = body.get("prenom");
		String email = body.get("email");
		String password = body.get("password");
		String tel = body.get("tel");
		String sexe = body.get("sexe");
		String date = body.get("date");

		String role1 = body.get("role");
		int roleId = Integer.parseInt(role1);
		Role role = roleRepository.findById(roleId);

		String service1 = body.get("service");
		int serviceId = Integer.parseInt(service1);
		Service service = serviceRepository.findById(serviceId);

		return employeeRepository.save(new Employee(nom,prenom,email,password,tel,sexe,date,role,service));
	}
	

	@RequestMapping(value = "/taches/{id}", method = RequestMethod.GET)
	public @ResponseBody
	List<TacheEmployee> getEmployeeTache(@PathVariable("id") String id) {


		return tacheEmployeeRepository.EmployeeTache(id);
	}
	
	@RequestMapping(value = "/tacheDone/{id}", method = RequestMethod.GET)
	public @ResponseBody
	List<TacheEmployee> TacheDone(@PathVariable("id") String id) {


		return tacheEmployeeRepository.EmployeeTacheDone(id);

	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Role getEmployeeRole(@PathVariable("id") String id) {
//		Role employeeRole = null;
//		try {
//			employeeRole = show(id).getRole();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return employeeRole;
		return null;
	}

	@RequestMapping(value = "/service/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Service getEmployeeService(@PathVariable("id") String id) {
		Service employeeService = null;
		try {
			employeeService = show(id).getService();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeService;
	}


	@GetMapping("/{id}")
	public Employee show(@PathVariable String id){
		int blogId = Integer.parseInt(id);
		return employeeRepository.findById(blogId);
	}

	@RequestMapping(value="/login",method=RequestMethod.POST, consumes={"text/html","application/x-www-form-urlencoded"})
    public Employee enrollmentSubmission(HttpServletRequest request, HttpServletResponse response,
										 @RequestBody MultiValueMap<String,String> parametersMultiMap){

        Map<String,String> formParameters = parametersMultiMap.toSingleValueMap();
        String email = formParameters.get("email");
		String password = formParameters.get("password");

		Employee employee = null;
		try {

			employee = employeeRepository.findByEmailEqualsAndPasswordEquals(email,password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
    }

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Employee> getEmployee() {


		return employeeRepository.findAll();
	}


	@DeleteMapping("/{id}")
	public Status delete(@PathVariable String id){
		int commId = Integer.parseInt(id);


		try {
			employeeRepository.delete(employeeRepository.findById(commId));
			return new Status(1, "Commentaire deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString()+ employeeRepository.findById(commId).getNom());
		}
	}
}
