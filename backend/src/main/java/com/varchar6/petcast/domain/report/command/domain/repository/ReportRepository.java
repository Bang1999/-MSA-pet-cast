package com.varchar6.petcast.domain.report.command.domain.repository;

import com.varchar6.petcast.domain.report.command.domain.aggregate.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}
