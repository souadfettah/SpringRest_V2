package com.lms.controllers;

import com.lms.models.LeaveDetails;
import com.lms.models.Projet;
import com.lms.repository.ServiceRepository;
import com.lms.service.ProjectManagerService;
import com.lms.service.ServiceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class ProjectManagerController {
    @Autowired
    private ProjectManagerService projectManageService;
@Autowired
private ServiceManageService servicemanage;


    @Autowired

    ServiceRepository serviceRepository;


    @RequestMapping(value = "/user/apply-project", method = RequestMethod.GET)
    public ModelAndView applyProject(ModelAndView mav) {

           /* projet = projectManageService.getUserById(id);
            mav.addObject("userService",projet.getService());*/

        mav.addObject("ServiceList",  servicemanage.getAllActiveServices());
        mav.addObject("project", new Projet());
       // mav.addObject("userRole",employee.getRole());

        mav.setViewName("createproject");
        return mav;
    }

    @RequestMapping(value = "/user/apply-project", method = RequestMethod.POST,consumes={"text/html","application/x-www-form-urlencoded"})
    public ModelAndView submitApplyLeave(ModelAndView mav, @Valid Projet projet,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            mav.setViewName("createproject"+projet.getService().getId());
        } else {


            projectManageService.applyProject(projet);
            mav.addObject("successMessage", "Your Leave Request is registered!" + projet.getService().getId());
            mav.setView(new RedirectView("/user/home"));
        }
        return mav;
    }




    @RequestMapping(value="/user/manage-projects",method= RequestMethod.GET)
    public ModelAndView manageProjects(ModelAndView mav) {

        mav.addObject("ProjectList", projectManageService.getAllActiveProjects());
        mav.setViewName("manageProjects");
        return mav;
    }

    @RequestMapping(value = "/user/manage-projects/{action}/{id}", method = RequestMethod.GET)
    public ModelAndView acceptOrRejectLeaves(ModelAndView mav, @PathVariable("action") String action,
                                             @PathVariable("id") int id) {
        Projet projet = projectManageService.getProjectDetailsOnId(id);
        if (action.equals("accept")) {
            projet.setAcceptRejectFlag(true);
            projet.setActive(false);
        } else if (action.equals("reject")) {
            projet.setAcceptRejectFlag(false);
            projet.setActive(false);
        }
        projectManageService.updateLeaveDetails(projet);
        mav.addObject("successMessage", "Updated Successfully!");
        mav.addObject("etat",projet.isAcceptRejectFlag());
        mav.setView(new RedirectView("/user/manage-projects"));
        return mav;
    }


}
