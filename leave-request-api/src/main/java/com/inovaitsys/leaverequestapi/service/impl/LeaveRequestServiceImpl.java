package com.inovaitsys.leaverequestapi.service.impl;

import com.inovaitsys.leaverequestapi.dto.LeaveRequestDto;
import com.inovaitsys.leaverequestapi.entity.LeaveRequest;
import com.inovaitsys.leaverequestapi.entity.User;
import com.inovaitsys.leaverequestapi.exceptions.ApplicationException;
import com.inovaitsys.leaverequestapi.repository.LeaveRequestRepository;
import com.inovaitsys.leaverequestapi.service.JwtService;
import com.inovaitsys.leaverequestapi.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        return leaveRequestEntityToDto(savedLeaveRequest);
    }

    @Override
    public LeaveRequestDto updateLeaveRequest(LeaveRequestDto requestDto) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(requestDto.getId())
                .orElseThrow(() -> new ApplicationException("Leave request not found"));

        checkResourceAllowedToAccess(leaveRequest);

        leaveRequest.setLeaveType(requestDto.getLeaveType());
        leaveRequest.setStartDate(requestDto.getStartDate());
        leaveRequest.setEndDate(requestDto.getEndDate());
        leaveRequest.setReason(requestDto.getReason());

        leaveRequestRepository.save(leaveRequest);

        return requestDto;
    }

    @Override
    public LeaveRequestDto deleteLeaveRequest(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Leave request not found"));

        checkResourceAllowedToAccess(leaveRequest);

        LeaveRequestDto leaveRequestDto = leaveRequestEntityToDto(leaveRequest);

        leaveRequestRepository.delete(leaveRequest);

        return leaveRequestDto;
    }

    @Override
    public List<LeaveRequestDto> getAllUserLeaveRequests() {
        User currentUser = jwtService.getCurrentUser();

        List<LeaveRequest> userLeaveRequests = leaveRequestRepository.findAllByUser_Id(currentUser.getId());
        return userLeaveRequests.stream().map(this::leaveRequestEntityToDto).collect(Collectors.toList());
    }

    @Override
    public LeaveRequestDto getLeaveRequestById(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Leave request not found"));

        checkResourceAllowedToAccess(leaveRequest);

        return leaveRequestEntityToDto(leaveRequest);
    }

    private LeaveRequestDto leaveRequestEntityToDto(LeaveRequest leaveRequest) {
        return new LeaveRequestDto(
                leaveRequest.getId(),
                leaveRequest.getLeaveType(),
                leaveRequest.getStartDate(),
                leaveRequest.getEndDate(),
                leaveRequest.getReason(),
                leaveRequest.getUser().getId()
        );
    }

    private void checkResourceAllowedToAccess(LeaveRequest leaveRequest) {
        User currentUser = jwtService.getCurrentUser();
        if (!currentUser.getId().equals(leaveRequest.getUser().getId())) {
            throw new ApplicationException("You are not allowed to perform this action");
        }
    }
}
