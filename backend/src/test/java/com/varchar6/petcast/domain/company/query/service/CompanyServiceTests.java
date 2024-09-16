package com.varchar6.petcast.domain.company.query.service;

import com.varchar6.petcast.domain.company.query.dto.CategoryResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompanyServiceTests {
    @Autowired
    private CompanyService companyService;

    @Test
    public void 카테고리_조회_테스트() {
        CategoryResponseDTO categoryResponseDTO = companyService.getCategories("1");
        System.out.println(categoryResponseDTO);
    }
}