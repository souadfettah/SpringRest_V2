package com.lms.controllers;

import com.lms.models.*;
import com.lms.repository.*;
import com.lms.service.CommentaireService;
import com.lms.service.LeaveManageService;
import com.lms.service.UserInfoService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.*;

@Controller
public class AdminController {

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	CommentaireService commentaireService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	LeaveManageService leaveManageService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	LeaveManageRepository leaveManageRepository;

	@RequestMapping(value = "/user/change-password", method = RequestMethod.GET)
	public ModelAndView changePasswordForm(ModelAndView mav) {

		mav.setViewName("changePassword");
		return mav;
	}

	@RequestMapping(value = "/user/change-password", method = RequestMethod.POST)
	public ModelAndView changePasswordSubmit(@RequestParam("currentPassword") String current_password,
											 @RequestParam("newPassword") String new_password) {
		ModelAndView mav = new ModelAndView();
		BCryptPasswordEncoder bCryptPassEncoder = new BCryptPasswordEncoder();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Employee employee = userInfoService.findUserByEmail(username);
		String encodedPassword = employee.getPassword();
		if (!bCryptPassEncoder.matches(current_password, encodedPassword)) {
			mav.addObject("successMessage", "Current Password entered is wrong!!!");
			mav.setView(new RedirectView("../user/change-password"));
			return mav;
		}
		employee.setPassword(new_password);
		userInfoService.saveUser(employee);
		mav.addObject("successMessage", "Password changed Successfully!!!");
		mav.setView(new RedirectView("../user/home"));
		return mav;
	}

	@RequestMapping(value = "/user/manage-users", method = RequestMethod.GET)
	public ModelAndView showManageUsers(ModelAndView mav) {
		List<Employee> userList = userInfoService.getUsers();
		mav.addObject("users", userList);
		mav.setViewName("manageUsers");
		return mav;
	}

	@GetMapping("/user/show-task/{id}")
	public ModelAndView showMyLeaves(ModelAndView mav, @PathVariable("id") int id) {
		LeaveDetails task = leaveManageService.getTaskById(id);
		List<Commentaire> CommentaireList;
		CommentaireList = commentaireService.getTaskComments(id);
		int size = CommentaireList.size();
		mav.addObject("task", task);
		mav.addObject("size", ""+size);
		mav.addObject("comment", new Commentaire());
		mav.addObject("comments", CommentaireList);
		mav.setViewName("Taskdetails");
		return mav;
	}

	@PostMapping("/user/show-task/{id}")
	public String create(ModelAndView mav, @PathVariable("id") int task_id , @ModelAttribute(value ="comment") Commentaire commentaire){
		LeaveDetails task = leaveManageService.getLeaveDetailsOnId(task_id);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Employee employee = userInfoService.findUserByEmail(username);
		String message = commentaire.getMessage();
		commentaire = new Commentaire("QUOTIDIEN",message,employee,task);
		commentaireService.saveCommentaire(commentaire);
		return "manageLeaves";
	}

	@RequestMapping(value = "/user/tache-edit/{id}", method = RequestMethod.GET)
	public ModelAndView editTaches(ModelAndView mav,
								   @PathVariable("id") int id) {


		//String iscomplete = "true";
		LeaveDetails tache = new LeaveDetails();
		tache = leaveManageService.getTaskById(id);
//		Role iscomplete = new Role();

//		mav.addObject("userTache",tache);
//		mav.addObject("isc	omplete",iscomplete);
////		mav.addObject("idtache",idtache);

		mav.addObject("userTache", tache);
		mav.setViewName("tacheEdit");
		return mav;
	}




	@RequestMapping(value = "/user/tache-edit/save", method = RequestMethod.POST)
	public ModelAndView saveUserEdit(ModelAndView mav,@Valid LeaveDetails tache, BindingResult bindResult) {



		//System.out.println("tist"+tache.getCompleted()+tache.getId());
		LeaveDetails tacheupdate = leaveManageService.getTaskById(tache.getId());
		if(tache.getCompleted().equals("1"))
		{
			tacheupdate.setCompleted("1");
		}
		else tacheupdate.setCompleted("0");

		leaveManageRepository.save(tacheupdate);

		mav.setView(new RedirectView("../my-leaves"));
		return mav;
	}

	@RequestMapping(value = "/user/manage-users/{action}/{id}", method = RequestMethod.GET)
	public ModelAndView manageUsers(ModelAndView mav, @PathVariable("action") String action,
									@PathVariable("id") int id) {

		Employee employee = null;
		if (action.equals("edit")) {
			employee = userInfoService.getUserById(id);
			mav.addObject("userInfo", employee);
			mav.addObject("userRole",employee.getRole());
			mav.setViewName("editUser");

		} else if (action.equals("delete")) {
			userInfoService.deleteUser(id);
			mav.addObject("successMessage", "User removed successfully!!");
			mav.setView(new RedirectView("/user/manage-users"));
		} else if (action.equals("block")) {
			userInfoService.blockUser(id);
			mav.addObject("successMessage", "User blocked successfully!!");
			mav.setView(new RedirectView("/user/manage-users"));
		} else if (action.equals("unblock")) {
			userInfoService.unBlockUser(id);
			mav.addObject("successMessage", "User is active now!!");
			mav.setView(new RedirectView("/user/manage-users"));
		}

		return mav;
	}

	@RequestMapping(value = "/user/manage-users/save-user-edit", method = RequestMethod.POST)
	public ModelAndView saveUserEdit(ModelAndView mav, @Valid Employee employee, @Valid Role role, BindingResult bindResult) {




		if (bindResult.hasErrors()) {
			mav.addObject("errorField", bindResult.getFieldError().getField());
			mav.addObject("errorMessage", bindResult.getFieldError().getDefaultMessage());
			mav.setView(new RedirectView("/docs-app/admin/manage-users/edit/" + employee.getId() + ""));
		} else {

			Employee employeeupdate = userInfoService.findUserByEmail(employee.getEmail());
			Role role1 = employeeupdate.getRole();
			role1.setName(role.getName());
			roleRepository.save(role1);
			employeeupdate.setNom(employee.getNom());
			employeeupdate.setPrenom(employee.getPrenom());
			employeeupdate.setRole(role1);
			employeeRepository.save(employeeupdate);
			mav.addObject("successMessage", "User Details updated successfully!!");
			mav.setView(new RedirectView("/user/manage-users"));

		}

	/*User userExists = userService.findUserByEmail(user.getEmail());
	User updateUser = userService.getUserById(user.getId());

	if (userExists != null && !user.getEmail().equals(updateUser.getEmail())) {
	    bindResult.rejectValue("email", "error.user", "User already exists with Email id");
	}

	if (bindResult.hasErrors()) {
	    mav.addObject("errorField", bindResult.getFieldError().getField());
	    mav.addObject("errorMessage", bindResult.getFieldError().getDefaultMessage());
	    mav.setView(new RedirectView("/docs-app/admin/manage-users/edit/" + user.getId() + ""));
	} else {
	    updateUser.setName(user.getName());
	    updateUser.setLastName(user.getLastName());
	    updateUser.setEmail(user.getEmail());
	    updateUser.setPassword(user.getPassword());
	    updateUser.setRole(user.getRole());
	    userService.saveUser(updateUser);
	    mav.addObject("successMessage", "User Details updated successfully!!");
	    mav.setView(new RedirectView("/docs-app/admin/manage-users"));
	}
*/
		return mav;
	}


}
