package com.varchar6.petcast.serviceothers.domain.gather.query.service;

import com.varchar6.petcast.serviceothers.common.exception.CommonException;
import com.varchar6.petcast.serviceothers.common.exception.ErrorCode;
import com.varchar6.petcast.serviceothers.domain.gather.query.dto.GatherDTO;
import com.varchar6.petcast.serviceothers.domain.gather.query.dto.GatherDetailDTO;
import com.varchar6.petcast.serviceothers.domain.gather.query.mapper.GatherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "queryGatherServiceImpl")
public class GatherServiceImpl implements GatherService {

    private GatherMapper gatherMapper;

    @Autowired
    public GatherServiceImpl(GatherMapper gatherMapper) {
        this.gatherMapper = gatherMapper;
    }

    /**설명.
     *  고객은 본인이 생성한 모임과 초대된 모임들에 대해 조회가 가능하다.
     *  (모임 별 역할 테이블에 속한 고객만 조회 가능)
     *  Request: 회원 id
     *  Response: 회원이 모임 구성원으로 포함된 모임 목록 반환
     **/
    @Override
    @Transactional
    public List<String> findAllGather(int userId) {
        return gatherMapper.selectGatherById(userId).stream()
                .map(GatherDTO::getName)
                .collect(Collectors.toList());

    }

    /**설명.
     *  1. 모임을 조회하려는 고객이 해당 모임의 모임 구성원인지 db값과 비교해서 구성원 여부 체크
     *  2. 고객은 본인이 생성한 모임과 초대된 모임들에 대한 내용들을 조회할 수 있다.
     *  Request: 회원 id, 모임 id
     *  Response: 모임 정보, 구성원 정보
     **/
    @Override
    @Transactional
    public GatherDetailDTO findDetailGather(int gatherId) {
        GatherDTO gatherInfo = gatherMapper.selectGatherDetailById(gatherId);
        if(gatherInfo == null){
            throw new CommonException(ErrorCode.NOT_FOUND_GATHER);
        }
        List<String> memberInfo = gatherMapper.selectMembersById(gatherId);
        if(memberInfo == null){
            throw new CommonException(ErrorCode.NOT_FOUND_GATHER_DETAIL);
        }
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

    /**설명.
     *  모임 구성원에는 포함되지만 초대장 수락 상태가 아니라면 초대장 수락 거절 페이지로 보여줘야함(프론트엔드의 일)
     *  -> 백엔드는 해당 고객의 모임 구성원 포함 여부
     *  Request: 회원 id, 초대장 id
     *  Response: 모임 접근 가능 여부
     **/
    @Override
    @Transactional
    public Boolean isAccessTrueGather(int invitationId, int userId) {
        List<Integer> membersId = gatherMapper.selectGroupMembersIdById(invitationId, userId);
        if(membersId == null){
            throw new CommonException(ErrorCode.NOT_FOUND_GATHER_ACTIVE);
        }
        if (!membersId.isEmpty() && membersId.contains(userId)) {
            return true;
        }
        return false;
    }

    /**설명.
     *  1. 모임을 조회하려는 고객이 해당 모임의 구성원인지 db값과 비교해서 구성원 여부 체크
     *  2. 모임에 속한 구성원 정보를 알 수 있다.
     *  Request: 회원 id, 모임 id
     *  Response: 구성원 정보
     **/
    @Override
    @Transactional
    public List<String> findGroupMemberById(int gatherId) {
        List<String> memberList = gatherMapper.selectGroupMembersNameById(gatherId);
        if(memberList == null){
            throw new CommonException(ErrorCode.NOT_FOUND_GATHER_MEMBER);
        }
        return memberList;
    }

    /**설명.
     *  Map형태로 Key & Value를 받아 id 또는 멤버 역할을 조회할 수 있다.
     *  Request: 'selectValue: id or role', gather_id, member_id
     *  Response: id or role
     **/
    @Override
    @Transactional
    public Object findMemberRoleById(Map<String, Object> params) {
        if("id".equals(params.get("selectValue"))){
            return gatherMapper.selectMemberRoleById(params);
        }else {
            return gatherMapper.selectMemberRoleById(params);
        }
    }
}
