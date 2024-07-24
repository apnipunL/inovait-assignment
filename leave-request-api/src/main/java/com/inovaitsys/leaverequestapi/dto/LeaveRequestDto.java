package com.inovaitsys.leaverequestapi.dto;

import com.inovaitsys.leaverequestapi.enums.LeaveType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDto {
    private Long id;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private Long userId;
}
