package com.varchar6.petcast.domain.company.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.company.query.dto.response.CompanyResponseDTO;
import com.varchar6.petcast.domain.company.query.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "companyQueryController")
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<ResponseMessage> getCompanyList() {
        List<CompanyResponseDTO> companyResponseDTOList = companyService.getCompanyList();
        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("category list")
                                .result(companyResponseDTOList)
                                .build()
                );
    }

    @GetMapping("categories/{companyId}")
    public ResponseEntity<ResponseMessage> getCategoriesByCompanyId(@PathVariable String companyId) {
        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("category list")
                                .result(companyService.getCategories(companyId))
                                .build()
                );
    }

}
