package com.varchar6.petcast.domain.gather.query.service;

import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import com.varchar6.petcast.domain.gather.query.dto.GatherDetailDTO;
import com.varchar6.petcast.domain.gather.query.mapper.GatherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "queryGatherServiceImpl")
public class GatherServiceImpl implements GatherService {

    private GatherMapper gatherMapper;

    @Autowired
    public GatherServiceImpl(GatherMapper gatherMapper) {
        this.gatherMapper = gatherMapper;
    }

    public List<String> findAllGather(int userId) {

        return gatherMapper.selectGatherById(userId).stream()
                .map(GatherDTO::getName)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public GatherDetailDTO findDetailGather(int gatherId) {

        GatherDTO gatherInfo = gatherMapper.selectGatherDetailById(gatherId);
        List<String> memberInfo = gatherMapper.selectMembersById(gatherId);

        GatherDetailDTO gatherDetail = GatherDetailDTO.builder()
                .id(gatherId)
                .name(gatherInfo.getName())
                .content(gatherInfo.getContent())
                .url(gatherInfo.getUrl())
                .number(gatherInfo.getNumber())
                .updatedAt(gatherInfo.getUpdatedAt())
                .createdAt(gatherInfo.getCreatedAt())
                .active(gatherInfo.isActive())
                .members(memberInfo)
                .build();

        return gatherDetail;
    }

    @Override
    @Transactional
    public Boolean isAccessTrueGather(int invitationId, int userId) {
        List<Integer> membersId = gatherMapper.selectGroupMembersIdById(invitationId, userId);
        if (!membersId.isEmpty() && membersId.contains(userId)) {
            return true;
        }
        return false;
    }

    public List<String> findGroupMemberById(int gatherId) {
        return gatherMapper.selectGroupMembersNameById(gatherId);
    }

    @Override
    @Transactional
    public Object findMemberRoleById(Map<String, Object> params) {

        if(params.containsKey("id")){
            return (Integer) gatherMapper.selectMemberRoleById(params);
        }else {
            return (String) gatherMapper.selectMemberRoleById(params);
        }
    }
}
