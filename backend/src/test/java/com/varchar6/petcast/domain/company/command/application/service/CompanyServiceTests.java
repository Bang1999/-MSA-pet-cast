package com.varchar6.petcast.domain.company.command.application.service;

import com.varchar6.petcast.domain.company.command.application.dto.request.CompanyEnrollRequestDTO;
import com.varchar6.petcast.domain.company.command.application.dto.response.CompanyResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceTests {

    @Autowired
    CompanyService companyService;

    @Test
    @Transactional
    void 업체_생성_요청() {
        CompanyEnrollRequestDTO companyEnrollRequestDTO = new CompanyEnrollRequestDTO();

        companyEnrollRequestDTO.setName("테스트 업체 이름");
        companyEnrollRequestDTO.setAddress("테스트 업체 주소");
        companyEnrollRequestDTO.setEmployeeNumber(100);
        companyEnrollRequestDTO.setCareer(1);
        companyEnrollRequestDTO.setLicense("테스트 사업자등록증");
        companyEnrollRequestDTO.setIntroduction("테스트 업체 소개");
        companyEnrollRequestDTO.setContactableTime("테스트 업체 연락가능시간");
        companyEnrollRequestDTO.setMemberId(1);

        CompanyResponseDTO companyResponseDTO = companyService.applyEnroll(companyEnrollRequestDTO);
        System.out.println("companyResponseDTO: " + companyResponseDTO);

        assertNotNull(companyResponseDTO);

    }

}