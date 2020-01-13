package com.lms.service;

import com.lms.models.Employee;
import com.lms.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userInfoService")
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Employee getUserInfo(){

	 return this.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	
    }
    
    public Employee findUserByEmail(String email) {
	return userInfoRepository.findByEmail(email);
    }

    public void saveUser(Employee employee) {
	employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
	employee.setActive(false);
	userInfoRepository.save(employee);

    }

    public List<Employee> getUsers() {

	return userInfoRepository.findAllByOrderById();
    }

    public Employee getUserById(int id) {

	return userInfoRepository.findById(id);
    }

    public void deleteUser(int id) {
	userInfoRepository.delete(id);
    }

    public void blockUser(int id) {

	userInfoRepository.blockUser(id);

    }

    public void unBlockUser(int id) {

	userInfoRepository.unBlockUser(id);
    }

}
