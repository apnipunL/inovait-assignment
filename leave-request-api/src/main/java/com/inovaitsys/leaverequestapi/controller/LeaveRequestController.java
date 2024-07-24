package com.inovaitsys.leaverequestapi.controller;

import com.inovaitsys.leaverequestapi.dto.LeaveRequestDto;
import com.inovaitsys.leaverequestapi.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping("")
    public ResponseEntity<LeaveRequestDto> createLeaveRequest(@RequestBody LeaveRequestDto requestDto) {
        return ResponseEntity.ok(leaveRequestService.createLeaveRequest(requestDto));
    }

    @PutMapping("")
    public ResponseEntity<LeaveRequestDto> updateLeaveRequest(@RequestBody LeaveRequestDto requestDto) {
        return ResponseEntity.ok(leaveRequestService.updateLeaveRequest(requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> deleteLeaveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.deleteLeaveRequest(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> getLeaveRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.getLeaveRequestById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<LeaveRequestDto>> getAllUserLeaveRequests() {
        return ResponseEntity.ok(leaveRequestService.getAllUserLeaveRequests());
    }

}
