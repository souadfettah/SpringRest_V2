package com.lms.service;

import com.lms.models.Employee;
import com.lms.models.LeaveDetails;
import com.lms.models.Projet;
import com.lms.repository.LeaveManageNativeSqlRepo;
import com.lms.repository.LeaveManageRepository;
import com.lms.repository.ProjectManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "projectManagerService")

public class ProjectManagerService {
    @Autowired
    private ProjectManagerRepository projectManageRepository;

    @Autowired
    private LeaveManageNativeSqlRepo projectManageNativeRepo;

    @SuppressWarnings("deprecation")
    public void applyProject(Projet projet) {
projet.setBudget("hhhh");
projet.setAcceptRejectFlag(false);
projet.setActive(false);
//projet.setService(new com.lms.models.Service("info"));
        projectManageRepository.save(projet);
    }
    public List<Projet> getAllActiveProjects() {

        return projectManageRepository.getAllActiveProjects();
    }
    public Projet getProjectDetailsOnId(int id) {
    return projectManageRepository.findOne(id);
    }
    public void updateLeaveDetails(Projet projet) {

        projectManageRepository.save(projet);

    }

    public Projet getProjetById(int id)
    {
        return projectManageRepository.findById(id);
    }


}
