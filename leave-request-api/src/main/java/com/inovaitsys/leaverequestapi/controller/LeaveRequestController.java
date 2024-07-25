package com.inovaitsys.leaverequestapi.controller;

import com.inovaitsys.leaverequestapi.dto.LeaveRequestDto;
import com.inovaitsys.leaverequestapi.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping("")
    public ResponseEntity<LeaveRequestDto> createLeaveRequest(@RequestBody LeaveRequestDto requestDto) {
        log.info("LeaveRequestController::createLeaveRequest -> {}", requestDto);

        return ResponseEntity.ok(leaveRequestService.createLeaveRequest(requestDto));
    }

    @PutMapping("")
    public ResponseEntity<LeaveRequestDto> updateLeaveRequest(@RequestBody LeaveRequestDto requestDto) {
        log.info("LeaveRequestController::updateLeaveRequest -> {}", requestDto);

        return ResponseEntity.ok(leaveRequestService.updateLeaveRequest(requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> deleteLeaveRequest(@PathVariable Long id) {
        log.info("LeaveRequestController::deleteLeaveRequest -> {}", id);

        return ResponseEntity.ok(leaveRequestService.deleteLeaveRequest(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> getLeaveRequestById(@PathVariable Long id) {
        log.info("LeaveRequestController::getLeaveRequestById -> {}", id);

        return ResponseEntity.ok(leaveRequestService.getLeaveRequestById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<LeaveRequestDto>> getAllUserLeaveRequests() {
        log.info("LeaveRequestController::getAllUserLeaveRequests");

        return ResponseEntity.ok(leaveRequestService.getAllUserLeaveRequests());
    }

}
