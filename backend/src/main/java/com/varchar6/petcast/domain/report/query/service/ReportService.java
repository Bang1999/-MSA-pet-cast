package com.varchar6.petcast.domain.report.query.service;

import com.varchar6.petcast.domain.report.query.dto.ReportDTO;

import java.util.List;

public interface ReportService {
    List<ReportDTO> getAllReports();

    List<ReportDTO> getReportByReporterId(Integer reporterId);

    List<ReportDTO> getReportByRespondentId(Integer respondentId);
}
