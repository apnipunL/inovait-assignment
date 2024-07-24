package com.inovaitsys.leaverequestapi.service.impl;

import com.inovaitsys.leaverequestapi.dto.LeaveRequestDto;
import com.inovaitsys.leaverequestapi.entity.LeaveRequest;
import com.inovaitsys.leaverequestapi.repository.LeaveRequestRepository;
import com.inovaitsys.leaverequestapi.service.JwtService;
import com.inovaitsys.leaverequestapi.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final JwtService jwtService;

    @Override
    public LeaveRequestDto createLeaveRequest(LeaveRequestDto requestDto) {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setLeaveType(requestDto.getLeaveType());
        leaveRequest.setStartDate(requestDto.getStartDate());
        leaveRequest.setEndDate(requestDto.getEndDate());
        leaveRequest.setReason(requestDto.getReason());
        leaveRequest.setUser(jwtService.getCurrentUser());
        LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);

        return new LeaveRequestDto(
                savedLeaveRequest.getId(),
                savedLeaveRequest.getLeaveType(),
                savedLeaveRequest.getStartDate(),
                savedLeaveRequest.getEndDate(),
                savedLeaveRequest.getReason(),
                savedLeaveRequest.getUser().getId()
        );
    }

    @Override
    public LeaveRequestDto updateLeaveRequest(LeaveRequestDto requestDto) {
        return null;
    }

    @Override
    public LeaveRequestDto deleteLeaveRequest(Long id) {
        return null;
    }

    @Override
    public List<LeaveRequestDto> getAllUserLeaveRequests() {
        return null;
    }

    @Override
    public LeaveRequestDto getLeaveRequestById(Long id) {
        return null;
    }
}
