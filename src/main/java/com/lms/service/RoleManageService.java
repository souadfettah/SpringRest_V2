package com.lms.service;

import com.lms.models.Role;
import com.lms.models.Service;
import com.lms.repository.RoleRepository;
import com.lms.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service(value = "roleManageService")

public class RoleManageService {

    @Autowired
    private RoleRepository roleRepository;
    public Role getServiceById(int id) {
        return roleRepository.findById(id);
    }
    public List<Role> getAllActiveRoles() {

        return roleRepository.getAllActiveRoles();
    }
}
