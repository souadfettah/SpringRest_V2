package com.lms.service;

import com.lms.models.Employee;
import com.lms.models.Projet;
import com.lms.models.Service;
import com.lms.repository.ServiceRepository;
import com.lms.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service(value = "roleManagerService")

public class ServiceManageService {

    @Autowired
    private ServiceRepository userInfoRepository;
    public Service getServiceById(int id) {
        return userInfoRepository.findById(id);
    }
    public List<Service> getAllActiveServices() {

        return userInfoRepository.getAllActiveServices();
    }
}
