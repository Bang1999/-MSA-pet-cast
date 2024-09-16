package com.varchar6.petcast.domain.request.query.service;

import com.varchar6.petcast.domain.request.query.dto.CompanyAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.MemberAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.RequestDetailDTO;

import java.util.List;

public interface RequestsService {

    List<String> findCategoryList();

    List<MemberAndRequestDTO> findAllRequestsByMemberId(int userId);

    List<CompanyAndRequestDTO> findAllRequestsByCompanyId(int companyId);

    RequestDetailDTO findRequestById(int requestId);
}
