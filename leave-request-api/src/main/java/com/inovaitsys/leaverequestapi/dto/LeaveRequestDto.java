package com.inovaitsys.leaverequestapi.dto;

import com.inovaitsys.leaverequestapi.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDto {
    private Long id;
    private LeaveType leaveType;
    private Date startDate;
    private Date endDate;
    private String reason;
    private Long userId;
}
