package com.varchar6.petcast.domain.company.query.mapper;

import com.varchar6.petcast.domain.company.query.service.vo.CategoryVO;
import com.varchar6.petcast.domain.company.query.service.vo.CompanyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    List<CategoryVO> findCategoriesByCompanyId(String companyId);
    CompanyVO findCompanyInformationByCompanyId(int companyId);
    List<CompanyVO> findAllCompanies();
}
