package com.example.gtech.controller;

import com.example.gtech.dto.ReportResponseDto;
import com.example.gtech.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportResponseDto>> getReport(
        @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        return new ResponseEntity<>(reportService.getReport(startDate, endDate), HttpStatus.OK);
    }
}
