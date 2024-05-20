package com.mr.softwares.assignment.librarysystem.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mr.softwares.assignment.librarysystem.dl.BookRepository;
import com.mr.softwares.assignment.librarysystem.dl.BorrowingRecordRepository;
import com.mr.softwares.assignment.librarysystem.dl.FineRepository;
import com.mr.softwares.assignment.librarysystem.dl.UserRepository;

@RestController

public class AnalyticsService
 {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FineRepository fineRepository;

    @GetMapping("/analytics/mostBorrowedBooks")
    public List<Object[]> mostBorrowedBooks() {
    
        return borrowingRecordRepository.findMostBorrowedBooks();
    }

    @GetMapping("/analytics/activeUsers")
    public List<?> activeUsers() {
    
        return userRepository.findActiveUsers();
    }

    @GetMapping("/analytics/overdueBooks")
    public List<?> overdueBooks() {
    
        return borrowingRecordRepository.findOverdueBooks();
    }

    
}

