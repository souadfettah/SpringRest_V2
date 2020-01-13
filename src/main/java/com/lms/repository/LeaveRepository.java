package com.lms.repository;

        import com.lms.models.LeaveDetails;
        import com.lms.models.Tache;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;

        import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveDetails, Integer> {

    LeaveDetails findById(int id);


}
