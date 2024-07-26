package com.inovaitsys.leaverequestapi.service;

import com.inovaitsys.leaverequestapi.entity.LeaveRequest;
import com.inovaitsys.leaverequestapi.entity.User;
import com.inovaitsys.leaverequestapi.enums.LeaveType;
import com.inovaitsys.leaverequestapi.exceptions.ApplicationException;
import com.inovaitsys.leaverequestapi.repository.LeaveRequestRepository;
import com.inovaitsys.leaverequestapi.service.impl.JwtServiceImpl;
import com.inovaitsys.leaverequestapi.service.impl.LeaveRequestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LeaveRequestServiceTest {

    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @Mock
    private JwtServiceImpl jwtService;

    @InjectMocks
    private LeaveRequestServiceImpl leaveRequestService;

    @Test
    void testGetLeaveRequestOfADifferentUser() {

        Mockito.when(leaveRequestRepository.findById(5L)).thenReturn(Optional.of(new LeaveRequest(
                1L,
                LeaveType.CASUAL,
                new Date(),
                new Date(),
                "Test reason",
                new User(3L, "testUser", null)
        )));

        Mockito.when(jwtService.getCurrentUser()).thenReturn(new User(4L, "testUser2", null));

        Assertions.assertThrows(ApplicationException.class, () -> leaveRequestService.getLeaveRequestById(5L));
    }

    @Test
    void testGetLeaveRequestOfTheSameUser() {

        Mockito.when(leaveRequestRepository.findById(5L)).thenReturn(Optional.of(new LeaveRequest(
                1L,
                LeaveType.CASUAL,
                new Date(),
                new Date(),
                "Test reason",
                new User(4L, "testUser2", null)
        )));

        Mockito.when(jwtService.getCurrentUser()).thenReturn(new User(4L, "testUser2", null));

        Assertions.assertDoesNotThrow(() -> leaveRequestService.getLeaveRequestById(5L));
    }

}
