package com.inovaitsys.leaverequestapi.service;

import com.inovaitsys.leaverequestapi.dto.LeaveRequestDto;

import java.util.List;

public interface LeaveRequestService {

    LeaveRequestDto createLeaveRequest(LeaveRequestDto requestDto);

    LeaveRequestDto updateLeaveRequest(LeaveRequestDto requestDto);

    LeaveRequestDto deleteLeaveRequest(Long id);

    List<LeaveRequestDto> getAllUserLeaveRequests();

    LeaveRequestDto getLeaveRequestById(Long id);

}
