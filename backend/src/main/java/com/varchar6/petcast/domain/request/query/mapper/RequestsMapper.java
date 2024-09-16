package com.varchar6.petcast.domain.request.query.mapper;

import com.varchar6.petcast.domain.request.query.dto.CompanyAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.MemberAndRequestDTO;
import com.varchar6.petcast.domain.request.query.dto.RequestDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RequestsMapper {
    List<String> selectCategoryList();

    List<MemberAndRequestDTO> selectAllRequestsByMemberId(@Param("user_id")int userId);

    List<CompanyAndRequestDTO> selectAllRequestsByCompanyId(@Param("company_id") int companyId);

    RequestDetailDTO selectRequestById(@Param("request_id") int requestId);
}