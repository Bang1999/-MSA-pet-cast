package com.varchar6.petcast.domain.company.query.service;

import com.varchar6.petcast.domain.company.query.service.vo.CategoryVO;
import com.varchar6.petcast.domain.company.query.dto.CategoryResponseDTO;
import com.varchar6.petcast.domain.company.query.dto.response.CompanyResponseDTO;
import com.varchar6.petcast.domain.company.query.mapper.CompanyMapper;
import com.varchar6.petcast.domain.company.query.service.vo.CompanyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service(value = "companyQueryService")
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @Override
    public CompanyResponseDTO getCompanyInformationByCompanyId(int companyId) {
        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

        CompanyVO companyVO = companyMapper.findCompanyInformationByCompanyId(companyId);

        companyResponseDTO.setId(companyVO.getId());
        companyResponseDTO.setName(companyVO.getName());
        companyResponseDTO.setAddress(companyVO.getAddress());
        companyResponseDTO.setEmployeeNumber(companyVO.getEmployeeNumber());
        companyResponseDTO.setCareer(companyVO.getCareer());
        companyResponseDTO.setLicense(companyVO.getLicense());
        companyResponseDTO.setIntroduction(companyVO.getIntroduction());
        companyResponseDTO.setContactableTime(companyVO.getContactableTime());
        companyResponseDTO.setCreatedAt(companyVO.getCreatedAt());
        companyResponseDTO.setUpdatedAt(companyVO.getUpdatedAt());
        companyResponseDTO.setActive(companyVO.isActive());
        companyResponseDTO.setApproved(companyVO.isApproved());
        companyResponseDTO.setMemberId(companyVO.getMemberId());

        return companyResponseDTO;
    }


    @Override
    public CategoryResponseDTO getCategories(String companyId) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCompanyId(companyId);
        categoryResponseDTO.setName(companyMapper.findCategoriesByCompanyId(companyId).stream()
                .map(CategoryVO::getName)
                .toList());

        return categoryResponseDTO;
    }

    @Override
    public List<CompanyResponseDTO> getCompanyList() {
        List<CompanyVO> companyVOList = companyMapper.findAllCompanies();
        return companyVOList.stream()
                .map(companyVO -> {
                    CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

                    companyResponseDTO.setId(companyVO.getId());
                    companyResponseDTO.setName(companyVO.getName());
                    companyResponseDTO.setAddress(companyVO.getAddress());
                    companyResponseDTO.setEmployeeNumber(companyVO.getEmployeeNumber());
                    companyResponseDTO.setCareer(companyVO.getCareer());
                    companyResponseDTO.setLicense(companyVO.getLicense());
                    companyResponseDTO.setIntroduction(companyVO.getIntroduction());
                    companyResponseDTO.setContactableTime(companyVO.getContactableTime());
                    companyResponseDTO.setCreatedAt(companyVO.getCreatedAt());
                    companyResponseDTO.setUpdatedAt(companyVO.getUpdatedAt());
                    companyResponseDTO.setActive(companyVO.isActive());
                    companyResponseDTO.setApproved(companyVO.isApproved());
                    companyResponseDTO.setMemberId(companyVO.getMemberId());
                    return companyResponseDTO;
                }).toList();
    }
}
