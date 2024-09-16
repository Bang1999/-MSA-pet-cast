package com.varchar6.petcast.domain.proposalandestimate.query.mapper;

import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateDTO;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateProposalDTO1;
import com.varchar6.petcast.domain.proposalandestimate.query.dto.EstimateProposalDTO2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EstimatesMapper {
    List<EstimateProposalDTO1> findAllEstimatesByMemberId(int memberId);

    List<EstimateDTO> findAllEstimatesByCompanyId(int companyId);

    EstimateProposalDTO2 findEstimateById(int estimateId);
}
