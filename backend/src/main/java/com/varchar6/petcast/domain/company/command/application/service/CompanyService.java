package com.varchar6.petcast.domain.company.command.application.service;

import com.varchar6.petcast.domain.company.command.application.dto.request.CompanyEnrollRequestDTO;
import com.varchar6.petcast.domain.company.command.application.dto.response.CompanyResponseDTO;
import org.apache.ibatis.javassist.NotFoundException;

public interface CompanyService {
    CompanyResponseDTO applyEnroll(CompanyEnrollRequestDTO companyEnrollRequestDTO);
    void deleteCompanyById(int companyId);

    void approveCompany(int companyId);

    void deactivateCompany(int companyId);
}
