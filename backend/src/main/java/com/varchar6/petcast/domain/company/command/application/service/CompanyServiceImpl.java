package com.varchar6.petcast.domain.company.command.application.service;

import com.varchar6.petcast.domain.company.command.application.dto.request.CompanyEnrollRequestDTO;
import com.varchar6.petcast.domain.company.command.application.dto.response.CompanyResponseDTO;
import com.varchar6.petcast.domain.company.command.domain.aggregate.Company;
import com.varchar6.petcast.domain.company.command.domain.repository.CategoryRepository;
import com.varchar6.petcast.domain.company.command.domain.repository.CompanyRepository;
import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleMember;
import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleMemberPk;
import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleType;
import com.varchar6.petcast.domain.member.command.domain.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service(value="commandCompanyService")
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;
    private final RoleRepository roleRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            CategoryRepository categoryRepository,
            RoleRepository roleRepository
    ) {
        this.companyRepository = companyRepository;
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public CompanyResponseDTO applyEnroll(CompanyEnrollRequestDTO companyEnrollRequestDTO) {
        return toResponseDTO(
                companyRepository.save(
                        toEntity(companyEnrollRequestDTO)
                )
        );
    }

    @Override
    @Transactional
    public void deleteCompanyById(int companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        companyRepository.delete(company);
    }

    @Override
    @Transactional
    public void approveCompany(int companyId) {

        Company company = companyRepository.findById(companyId).orElseThrow(IllegalAccessError::new);

        if (company.isApproved()) {
            throw new IllegalArgumentException("Company is already approved");
        }

        company.setApproved(true);
        company.setUpdatedAt(LocalDateTime.now().format(FORMATTER));

        // role 테이블 추가
        RoleMemberPk roleMemberPk = new RoleMemberPk(company.getMemberId(), RoleType.COMPANY.getRoleId());
        RoleMember roleMember = RoleMember.builder()
                .roleId(RoleType.COMPANY.getRoleId())
                .memberId(company.getMemberId())
                .build();
        Optional<RoleMember> isMemberRolePresent = roleRepository.findById(roleMemberPk);
        if (isMemberRolePresent.isEmpty()) {
            roleRepository.save(roleMember);
        }

        // company update
        companyRepository.save(company);
    }

    @Transactional
    public void deactivateCompany(int companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(IllegalAccessError::new);
        company.setActive(false);
        company.setUpdatedAt(LocalDateTime.now().format(FORMATTER));
        companyRepository.save(company);
    }


    @Transactional
    public void deleteCategoriesByCompanyId(int companyId) {
        categoryRepository.deleteByCompanyId(companyId);
    }

    private Company toEntity(CompanyEnrollRequestDTO companyEnrollRequestDTO) {
        return Company.builder()
                .name(companyEnrollRequestDTO.getName())
                .address(companyEnrollRequestDTO.getAddress())
                .employeeNumber(companyEnrollRequestDTO.getEmployeeNumber())
                .career(companyEnrollRequestDTO.getCareer())
                .license(companyEnrollRequestDTO.getLicense())
                .introduction(companyEnrollRequestDTO.getIntroduction())
                .contactableTime(companyEnrollRequestDTO.getContactableTime())
                .createdAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .updatedAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .active(true)
                .approved(false)
                .memberId(companyEnrollRequestDTO.getMemberId())
                .build();
    }

    private CompanyResponseDTO toResponseDTO(Company company) {
        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO();

        companyResponseDTO.setId(company.getId());
        companyResponseDTO.setName(company.getName());
        companyResponseDTO.setAddress(company.getAddress());
        companyResponseDTO.setEmployeeNumber(company.getEmployeeNumber());
        companyResponseDTO.setCareer(company.getCareer());
        companyResponseDTO.setLicense(company.getLicense());
        companyResponseDTO.setIntroduction(company.getIntroduction());
        companyResponseDTO.setContactableTime(company.getContactableTime());
        companyResponseDTO.setCreatedAt(company.getCreatedAt());
        companyResponseDTO.setUpdatedAt(company.getUpdatedAt());
        companyResponseDTO.setActive(company.isActive());
        companyResponseDTO.setApproved(company.isApproved());
        companyResponseDTO.setMemberId(company.getMemberId());


        return companyResponseDTO;
    }

}
