package com.varchar6.petcast.domain.report.query.mapper;

import com.varchar6.petcast.domain.report.query.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    List<ReportDTO> selectAllReports();

    List<ReportDTO> selectReportByReporterId(Integer reporterId);

    List<ReportDTO> selectReportByRespondentId(Integer respondentId);
}
