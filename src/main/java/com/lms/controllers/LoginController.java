package com.lms.controllers;

import com.lms.models.Employee;
import com.lms.service.LeaveManageService;
import com.lms.service.RoleManageService;
import com.lms.service.UserInfoService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * This controller will provide the basic operations fo users. Like
 * signing-in,registering a new user.
 * 
 * @author navinkumark
 *
 */
@Controller
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    LeaveManageService leaveManageService;
@Autowired
	RoleManageService roleManageService;

    /**
     * This method opens up the login page if user is not authenticated
     * otherwise redirects the user to user home page.
     * 
     * @return
     */
    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public ModelAndView login(ModelAndView mav) {

	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Employee employee = userInfoService.findUserByEmail(auth.getName());

	mav.addObject("userInfo", employee);
	if (!(auth instanceof AnonymousAuthenticationToken)) {
	    mav.setViewName("home");
	    return mav;
	}
	mav.setViewName("login");
	return mav;
    }

    /**
     * Opens the registration page to register a new user.
     * 
     * @return ModelAndView
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(ModelAndView mav) {

	Employee employee = new Employee();
		mav.addObject("RoleList",  roleManageService.getAllActiveRoles());

		mav.addObject("userInfo", employee);
	mav.setViewName("registration");
	return mav;
    }

    /**
     * Gets the form input from registration page and adds the user to the
     * database.
     * 
     * @param employee
     * @param bindResult
     * @return ModelAndView
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(ModelAndView mav, @Valid Employee employee, BindingResult bindResult) {

	Employee userExists = userInfoService.findUserByEmail(employee.getEmail());

	if (userExists != null) {
	    bindResult.rejectValue("email", "error.user", "User already exists with Email id");
	}

	if (bindResult.hasErrors()) {
	    mav.setViewName("registration");
	} else {

	    userInfoService.saveUser(employee);
	    mav.addObject("successMessage", "User registered successfully! Awaiting for Manager approval!!");
	    mav.setViewName("login");
	}
	return mav;
    }

    /**
     * Shows the admin page after user authentication is done.
     * 
     * @param request
     * @return ModelAndView
     * @throws JSONException
     */
    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView mav, HttpServletRequest request) throws Exception {

	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Employee employee = userInfoService.findUserByEmail(auth.getName());
	request.getSession().setAttribute("userInfo", employee);

	mav.addObject("userInfo", employee);
	mav.setViewName("home");
	return mav;

    }

}
