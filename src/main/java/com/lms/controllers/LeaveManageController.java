package com.lms.controllers;

import com.lms.models.Employee;
import com.lms.models.LeaveDetails;
import com.lms.service.LeaveManageService;
import com.lms.service.ProjectManagerService;
import com.lms.service.UserInfoService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Controller
public class LeaveManageController {

    @Autowired
    private LeaveManageService leaveManageService;

    @Autowired
    private UserInfoService userInfoService;
	@Autowired
	private ProjectManagerService projectManagerService;


    @RequestMapping(value = "/user/apply-leave", method = RequestMethod.GET)
    public ModelAndView applyLeave(ModelAndView mav) {
		mav.addObject("ProjectList",  projectManagerService.getAllActiveProjects());

	mav.addObject("leaveDetails", new LeaveDetails());
	mav.setViewName("applyLeave");
	return mav;
    }

    @RequestMapping(value = "/user/apply-leave", method = RequestMethod.POST)
    public ModelAndView submitApplyLeave(ModelAndView mav, @Valid LeaveDetails leaveDetails,
                                         BindingResult bindingResult) {

	Employee employee = userInfoService.getUserInfo();
	if (bindingResult.hasErrors()) {
	    mav.setViewName("applyLeave");
	} else {
	    leaveDetails.setUsername(employee.getEmail());
	    leaveDetails.setEmployeeName(employee.getNom() + " " + employee.getPrenom());
		System.out.println("leaave " + leaveDetails.getEmployeeName() +  " " +leaveDetails.getLeaveType() + " " + leaveDetails.getReason()+" "+leaveDetails.getFromDate()+" "+leaveDetails.getToDate()+ " "+leaveDetails.isActive()+ " "+ leaveDetails.getUsername()+ " "+leaveDetails.getDuration()+" "+leaveDetails.isAcceptRejectFlag());

		leaveManageService.applyLeave(leaveDetails);
	    mav.addObject("successMessage", "Your Leave Request is registered!");
	    mav.setView(new RedirectView("/user/home"));
	}
	return mav;
    }

    @RequestMapping(value = "/user/get-all-leaves", method = RequestMethod.GET)
    public @ResponseBody
    String getAllLeaves(@RequestParam(value = "pending", defaultValue = "false") boolean pending,
                        @RequestParam(value = "accepted", defaultValue = "false") boolean accepted,
                        @RequestParam(value = "rejected", defaultValue = "false") boolean rejected) throws Exception {

	Iterator<LeaveDetails> iterator = leaveManageService.getAllLeaves().iterator();
	if (pending || accepted || rejected)
	    iterator = leaveManageService.getAllLeavesOnStatus(pending, accepted, rejected).iterator();
	JSONArray jsonArr = new JSONArray();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = Calendar.getInstance();

	while (iterator.hasNext()) {
	    LeaveDetails leaveDetails = iterator.next();
	    calendar.setTime(leaveDetails.getToDate());
	    calendar.add(Calendar.DATE, 1);

	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("title", leaveDetails.getEmployeeName());
	    jsonObj.put("start", dateFormat.format(leaveDetails.getFromDate()));
	    jsonObj.put("end", dateFormat.format(calendar.getTime()));
	    if (leaveDetails.isActive())
		jsonObj.put("color", "#0878af");
	    if (!leaveDetails.isActive() && leaveDetails.isAcceptRejectFlag())
		jsonObj.put("color", "green");
	    if (!leaveDetails.isActive() && !leaveDetails.isAcceptRejectFlag())
		jsonObj.put("color", "red");
	    jsonArr.put(jsonObj);
	}

	return jsonArr.toString();
    }
    
    @RequestMapping(value="/user/manage-leaves",method= RequestMethod.GET)
    public ModelAndView manageLeaves(ModelAndView mav) {

	mav.addObject("leavesList", leaveManageService.getAllActiveLeaves());
	mav.setViewName("manageLeaves");
	return mav;
    }

    @RequestMapping(value = "/user/manage-leaves/{action}/{id}", method = RequestMethod.GET)
    public ModelAndView acceptOrRejectLeaves(ModelAndView mav, @PathVariable("action") String action,
                                             @PathVariable("id") int id) {
	LeaveDetails leaveDetails = leaveManageService.getLeaveDetailsOnId(id);
	if (action.equals("accept")) {
	    leaveDetails.setAcceptRejectFlag(true);
	    leaveDetails.setActive(false);
	} else if (action.equals("reject")) {
	    leaveDetails.setAcceptRejectFlag(false);
	    leaveDetails.setActive(false);
	}
	leaveManageService.updateLeaveDetails(leaveDetails);
	mav.addObject("successMessage", "Updated Successfully!");
	mav.setView(new RedirectView("/user/manage-leaves"));
	return mav;
    }

    @RequestMapping(value = "/user/my-leaves", method = RequestMethod.GET)
    public ModelAndView showMyLeaves(ModelAndView mav) {

	Employee employee = userInfoService.getUserInfo();
	List<LeaveDetails> leavesList = leaveManageService.getAllLeavesOfUser(employee.getEmail());
	mav.addObject("leavesList", leavesList);
	mav.setViewName("myLeaves");
	return mav;
    }
}
