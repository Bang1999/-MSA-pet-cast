package com.varchar6.petcast.domain.proposalandestimate.query.service;

import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateDTO;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateProposalDTO1;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateProposalDTO2;

import java.util.List;

public interface EstimateService {
    List<EstimateProposalDTO1> findAllEstimatesByMemberId(int memberId);

    List<EstimateDTO> findAllEstimatesByCompanyId(int companyId);

    EstimateProposalDTO2 findEstimateById(int estimateId);
}
