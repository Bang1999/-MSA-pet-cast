package com.varchar6.petcast.domain.proposalandestimate.query.service;

import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateDTO;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateProposalDTO1;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateProposalDTO2;
import com.varchar6.petcast.domain.proposalandestimate.query.mapper.EstimatesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstimateServiceImpl implements EstimateService{

    private final EstimatesMapper estimatesMapper;

    @Autowired
    public EstimateServiceImpl(EstimatesMapper estimatesMapper) {
        this.estimatesMapper = estimatesMapper;}

    // 고객이 보낸 견적서 목록 조회
    @Override
    public List<EstimateProposalDTO1> findAllEstimatesByMemberId(int memberId) {
        List<EstimateProposalDTO1> estimates = null;
        try {
            estimates = estimatesMapper.findAllEstimatesByMemberId(memberId);
        } catch (Exception e){
            throw new RuntimeException("[Service] findAllEstimatesByMemberId ERROR", e);
        }
        return estimates.stream().toList();

    }

    // 업체가 받은 견적서 목록 조회
    @Override
    public List<EstimateDTO> findAllEstimatesByCompanyId(int companyId) {
        List<EstimateDTO> estimates = null;
        try {
            estimates = estimatesMapper.findAllEstimatesByCompanyId(companyId);
        }catch (Exception e){
            throw new RuntimeException("[Service] findAllEstimatesByCompanyId ERROR", e);
        }
        return estimates.stream().toList();
    }

    // 견적서 상세 조회
//    @Override
    public EstimateProposalDTO2 findEstimateById(int estimateId) {
        return estimatesMapper.findEstimateById(estimateId);
    }
}
