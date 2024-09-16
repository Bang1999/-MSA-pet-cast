package com.varchar6.petcast.domain.report.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.report.query.dto.ReportDTO;
import com.varchar6.petcast.domain.report.query.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController(value = "queryReportController")
@RequestMapping("/api/v1/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/post")
    private ResponseEntity<ResponseMessage> getAllReports(){
        List<ReportDTO> responseReports = reportService.getAllReports();

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("모든 신고 조회 성공")
                                .result(responseReports)
                                .build()
                );
    }


    @GetMapping("/reporter/{reporterId}")
    private ResponseEntity<ResponseMessage> getReportByReporterId(@PathVariable Integer reporterId){
        List<ReportDTO> responseReports = reportService.getReportByReporterId(reporterId);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("신고자 신고 조회 성공")
                                .result(responseReports)
                                .build()
                );

    }

    @GetMapping("/respondent/{respondentId}")
    private ResponseEntity<ResponseMessage> getReportByRespondentId(@PathVariable Integer respondentId){
        List<ReportDTO> responseReports = reportService.getReportByRespondentId(respondentId);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("피신고자 신고 조회 성공")
                                .result(responseReports)
                                .build()
                );
    }



}
