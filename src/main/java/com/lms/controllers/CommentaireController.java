package com.lms.controllers;

import com.lms.models.*;
import com.lms.repository.CommentaireRepository;
import com.lms.repository.EmployeeRepository;
import com.lms.repository.LeaveRepository;
import com.lms.repository.TacheRepository;
import com.lms.service.CommentaireService;
import com.lms.service.LeaveManageService;
import com.lms.service.UserInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/commentaire")
public class CommentaireController {

	@Autowired
	CommentaireService commentaireService;

	@Autowired
	LeaveManageService leaveManageService;

	@Autowired
	LeaveRepository leaveRepository;

	@Autowired
	UserInfoService userInfoService;

	static final Logger logger = Logger.getLogger(CommentaireController.class);



	/*@PostMapping("/user/show-task/{id}")
	public String create(ModelAndView mav, @PathVariable("id") int task_id , @ModelAttribute(value ="comment") Commentaire commentaire){
		LeaveDetails task = leaveManageService.getLeaveDetailsOnId(task_id);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Employee employee = userInfoService.findUserByEmail(username);
		String message = commentaire.getMessage();
		commentaire = new Commentaire("QUOTIDIEN",message,employee,task);
		commentaireService.saveCommentaire(commentaire);
		return "manageLeaves";
	}*/

	/*@GetMapping("/user/show-task/{id}")
	public String showAddPersonPage(Model model) {

		Commentaire commentaire = new Commentaire();
		model.addAttribute("comment", commentaire);

		return "manageLeaves";
	}*/


	/*@GetMapping("/user/show-task/{id}")
	public ModelAndView showMyLeaves(ModelAndView mav, @PathVariable("id") int id,Commentaire comment) {
		LeaveDetails task = leaveRepository.findById(id);
		//auth = SecurityContextHolder.getContext().getAuthentication();
		//Set<String> roles = AuthorityUtils.authorityListToSet(auth.getAuthorities());
		List<Commentaire> CommentaireList;
		//String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//if(roles.contains("MANAGER")){

			CommentaireList = commentaireService.getTaskComments(id);

		/*}
		else
		{
			Employee employee = userInfoService.findUserByEmail(username);
			int userId = employee.getId();
			CommentaireList = commentaireService.getTaskCommentsByUser(id,userId);
			System.out.println("hna2 "+CommentaireList);

		}


		mav.addObject("task", task);
		mav.addObject("comments", CommentaireList);
		mav.setViewName("TaskDetails");
		return mav;
	}

	 */




	/*@GetMapping("/{id}")
	public Commentaire show(@PathVariable String id){
		int blogId = Integer.parseInt(id);
		return commentaireRepository.findById(blogId);
	}



	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Commentaire> getCommentaire() {

		return commentaireRepository.findAll();
	}



	@DeleteMapping("/{id}")
	public Status delete(@PathVariable String id){
		int commId = Integer.parseInt(id);

		try {
			commentaireRepository.delete(commId);
			return new Status(1, "Commentaire deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}

	 */
}
