package com.lms.repository;

import com.lms.models.LeaveDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LeaveManageNativeSqlRepo {

    @PersistenceContext
    EntityManager entityManager;


    @SuppressWarnings("unchecked")
    public List<LeaveDetails> getAllLeavesOnStatus(StringBuffer whereQuery) {

	Query query = entityManager.createNativeQuery("select * from leave_details where " + whereQuery,
		LeaveDetails.class);
	
	return query.getResultList();
    }
}
