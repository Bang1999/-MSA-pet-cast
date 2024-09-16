package com.varchar6.petcast.domain.gather.query.service;

import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import com.varchar6.petcast.domain.gather.query.dto.GatherDetailDTO;

import java.util.List;
import java.util.Map;

public interface GatherService {

    List<String> findAllGather(int userId);

    GatherDetailDTO findDetailGather(int gatherId);

    Boolean isAccessTrueGather(int invitationId, int userId);

    List<String> findGroupMemberById(int gatherId);

    Object findMemberRoleById(Map<String, Object> params);
}
