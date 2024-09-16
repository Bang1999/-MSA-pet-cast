package com.varchar6.petcast.domain.proposalandestimate.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateDTO;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateProposalDTO1;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateProposalDTO2;
import com.varchar6.petcast.domain.proposalandestimate.query.service.EstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/estimates")
public class EstimateController {

    private final EstimateService estimateService;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public EstimateController(EstimateService estimateService) {
        this.estimateService = estimateService;
    }


    // 고객 견적서 목록 조회
    @GetMapping("/list/member")
    public ResponseEntity<ResponseMessage> findAllEstimatesByMemberId(@@RequestAttribute("memberId") int memberId) {
        String message = "고객 견적서 목록 조회 성공!";
        List<EstimateProposalDTO1> estimates = null;
        try {
            estimates = estimateService.findAllEstimatesByMemberId(memberId);
        }catch (Exception e){
            message = "고객 견적서 목록 조회 실패!";
            throw new RuntimeException("고객 견적서 목록 조회 실패", e);
        }
        return ResponseEntity.ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(estimates)
                        .build());
    }

    // 업체 견적서 목록 조회
    @GetMapping("/list/company/{companyId}")
    public ResponseEntity<ResponseMessage> findAllEstimatesByCompanyId(@PathVariable int companyId) {
        String message = "업체가 보낸 견적서 목록 조회 성공!";
        List<EstimateDTO> estimates = null;
        try {
            estimates = estimateService.findAllEstimatesByCompanyId(companyId);
        }catch (Exception e){
            message = "업체가 보낸 견적서 목록 조회 실패!";
            throw new RuntimeException("업체 견적서 목록 조회 ERROR!", e);
        }
        return ResponseEntity.ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(estimates)
                        .build());
    }

    // 요청서 상세 조회
    @GetMapping("/list/detail/{estimateId}")
    public ResponseEntity<ResponseMessage> findEstimateById(@PathVariable int estimateId) {
        String message = "견적서 상세 조회 성공!";
        EstimateProposalDTO2 estimate = null;
        try {
            estimate = estimateService.findEstimateById(estimateId);
        }catch (Exception e){
            message = "견적서 상세 조회 실패!";
            throw new RuntimeException("견적서 상세 조회 실패!!", e);
        }
        return ResponseEntity.ok()
                .body(ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(message)
                        .result(estimate)
                        .build());
    }

}

