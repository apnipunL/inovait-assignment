package com.inovaitsys.leaverequestapi.repository;

import com.inovaitsys.leaverequestapi.entity.LeaveRequest;
import com.inovaitsys.leaverequestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findAllByUser_Id(Long userId);

}
