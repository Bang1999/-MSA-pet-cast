package com.varchar6.petcast.domain.report.query.service;

import com.varchar6.petcast.domain.report.query.dto.ReportDTO;
import com.varchar6.petcast.domain.report.query.mapper.ReportMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service(value="queryReportService")
public class ReportServiceImpl implements ReportService{
    private final ReportMapper reportMapper;

    @Autowired
    public ReportServiceImpl(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }
    @Override
    @Transactional
    public List<ReportDTO> getAllReports() { return reportMapper.selectAllReports();}

    @Override
    @Transactional
    public List<ReportDTO> getReportByReporterId(Integer reporterId) { return reportMapper.selectReportByReporterId(reporterId);}

    @Override
    @Transactional
    public List<ReportDTO> getReportByRespondentId(Integer respondentId) { return reportMapper.selectReportByRespondentId(respondentId);}
}
