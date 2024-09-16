package com.varchar6.petcast.domain.gather.command.application.service;

import com.varchar6.petcast.domain.gather.command.application.dto.request.*;
import com.varchar6.petcast.domain.gather.command.application.dto.response.*;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.GatherRole;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.Gather;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.GatherMember;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.Invitation;
import com.varchar6.petcast.domain.gather.command.domain.repository.GatherMemberRepository;
import com.varchar6.petcast.domain.gather.command.domain.repository.GatherRepository;
import com.varchar6.petcast.domain.gather.command.domain.repository.InvitationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@Service(value = "commandGatherServiceImpl")
public class GatherServiceImpl implements GatherService {

    private final GatherRepository gatherRepository;
    private final GatherMemberRepository gatherMemberRepository;
    private final InvitationRepository invitationRepository;
    private final ModelMapper modelMapper;
    private final com.varchar6.petcast.domain.gather.query.service.GatherService gatherService;

    @Autowired
    public GatherServiceImpl(GatherRepository gatherRepository,
                             GatherMemberRepository gatherMemberRepository,
                             InvitationRepository invitationRepository,
                             ModelMapper modelMapper,
                             com.varchar6.petcast.domain.gather.query.service.GatherService gatherService) {
        this.gatherRepository = gatherRepository;
        this.gatherMemberRepository = gatherMemberRepository;
        this.invitationRepository = invitationRepository;
        this.modelMapper = modelMapper;
        this.gatherService = gatherService;
    }

    @Override
    @Transactional
    public void createGather(RequestCreateGatherDTO requestCreateGatherDTO) {
        String currentDate = getNow();

        // 모임 테이블 insert 과정
        Gather gather = Gather.builder()
                .name(requestCreateGatherDTO.getName())
                .content(requestCreateGatherDTO.getContent())
                .number(requestCreateGatherDTO.getNumber())
                .url(requestCreateGatherDTO.getUrl())
                .updatedAt(currentDate)
                .createdAt(currentDate)
                .active(true)
                .build();
        Gather newGather = null;
        try {
            newGather = gatherRepository.save(gather);
        } catch (Exception e) {
            throw new RuntimeException("[Service] 새로운 모임 insert 실패!!", e);
        }

        try {
            GatherMember newGatherMember = GatherMember.builder()
                    .role(GatherRole.LEADER)
                    .gatherId(newGather.getId())
                    .memberId(requestCreateGatherDTO.getUserId())
                    .build();
            gatherMemberRepository.save(newGatherMember);
        } catch (Exception e) {
            throw new RuntimeException("[Service] 모임&회원 중간 테이블 insert 실패!!", e);
        }

    }

    @Override
    @Transactional
    public ResponseUpdateGatherInfoDTO updateGatherInfo(RequestUpdateGatherInfoDTO requestUpdateGatherDTO) {
        String currentDate = getNow();

        // 멤버 역할 꺼내오기
        Map<String, Object> params = new HashMap<>();
        params.put("selectValue", "role");
        params.put("gather_id", requestUpdateGatherDTO.getGatherId());
        params.put("member_id", requestUpdateGatherDTO.getUserId());
        String memberRole = (String) gatherService.findMemberRoleById(params);

        // 모임 수정
        ResponseUpdateGatherInfoDTO responseUpdateGatherInfoDTO = null;
        if (GatherRole.LEADER.toString().equals(memberRole)) {
            Gather updateGather = gatherRepository.findById(requestUpdateGatherDTO.getGatherId()).orElseThrow();

            updateGather.setName(requestUpdateGatherDTO.getName());
            updateGather.setContent(requestUpdateGatherDTO.getContent());
            updateGather.setNumber(requestUpdateGatherDTO.getNumber());
            updateGather.setUrl(requestUpdateGatherDTO.getUrl());
            updateGather.setUpdatedAt(currentDate);
            try {
                responseUpdateGatherInfoDTO = modelMapper.map(updateGather, ResponseUpdateGatherInfoDTO.class);
            } catch (Exception e) {
                throw new RuntimeException("[Service] 모임 정보 수정 중에 에러 발생!!", e);
            }
        }
        return responseUpdateGatherInfoDTO;
    }

    @Override
    @Transactional
    public ResponseDeactiveGatherDTO deactiveGather(RequestDeactiveGatherDTO requestDeactiveGatherDTO) {
        String currentDate = getNow();

        // Leader인지 확인
        Map<String, Object> params = new HashMap<>();
        params.put("selectValue", "role");
        params.put("gather_id", requestDeactiveGatherDTO.getGatherId());
        params.put("member_id", requestDeactiveGatherDTO.getUserId());
        String memberRole = (String) gatherService.findMemberRoleById(params);

        ResponseDeactiveGatherDTO responseDeactiveGatherDTO = null;
        if (GatherRole.LEADER.toString().equals(memberRole)) {
            Gather currentGather = gatherRepository.findById(requestDeactiveGatherDTO.getGatherId()).orElseThrow();
            currentGather.setActive(false);
            currentGather.setUpdatedAt(currentDate);

            try {
                responseDeactiveGatherDTO = modelMapper.map(currentGather, ResponseDeactiveGatherDTO.class);
            } catch (Exception e) {
                throw new RuntimeException("[Service] 비활성화 업데이트 중 에러 발생!!", e);
            }

        }
        return responseDeactiveGatherDTO;
    }

    @Override
    @Transactional
    public ResponseSendInvitaionDTO sendInvitation(RequestSendInvitationDTO requestInvitationDTO) {

        // 1. 해당 모임의 모임장인지 확인
        Map<String, Object> params = new HashMap<>();
        params.put("selectValue", "role");
        params.put("gather_id", requestInvitationDTO.getGatherId());
        params.put("member_id", requestInvitationDTO.getUserId());
        String memberRole = (String) gatherService.findMemberRoleById(params);

        // 2. insert
        ResponseSendInvitaionDTO responseSendInvitaionDTO = null;
        if (GatherRole.LEADER.toString().equals(memberRole)) {
            String currentDate = getNow();

            // 2. 초대장 테이블에 insert
            Invitation invitation = Invitation.builder()
                    .active(true)
                    .createdAt(currentDate)
                    .userId(requestInvitationDTO.getUserId())
                    .gatherId(responseSendInvitaionDTO.getGatherId())
                    .build();
            try {
                invitationRepository.save(invitation);
            } catch (Exception e) {
                throw new RuntimeException("[Service] 초대장 정보 저장 중에 에러 발생!!", e);
            }

            // 3. 문자 전송~


            try {
                responseSendInvitaionDTO = ResponseSendInvitaionDTO.builder()
                        .userId(requestInvitationDTO.getUserId())
                        .gatherId(requestInvitationDTO.getGatherId())
                        .build();
            } catch (Exception e) {
                throw new RuntimeException("[Service] return 만들다 실패", e);
            }
        }
        return responseSendInvitaionDTO;
    }

    @Override
    @Transactional
    public ResponseInvitationDTO acceptInvatation(RequestInvitationDTO requestInvitationDTO) {
        Invitation invitation = invitationRepository.findById(requestInvitationDTO
                .getInvitationId()).orElseThrow(() -> new NoSuchElementException("Invitation not found with id: " + requestInvitationDTO.getInvitationId()));

        invitation.setActive(true);
        ResponseInvitationDTO responseInvitationDTO = null;
        try {
            responseInvitationDTO = modelMapper.map(invitation, ResponseInvitationDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("[Service] 수락하다 실패!", e);
        }

        return responseInvitationDTO;
    }

    @Override
    @Transactional
    public ResponseInvitationDTO refuseInvatation(RequestInvitationDTO requestInvitationDTO) {
        Invitation invitation = invitationRepository.findById(requestInvitationDTO
                .getInvitationId()).orElseThrow(() -> new NoSuchElementException("Invitation not found with id: " + requestInvitationDTO.getInvitationId()));

        invitation.setActive(false);
        ResponseInvitationDTO responseInvitationDTO = null;
        try {
            responseInvitationDTO = modelMapper.map(invitation, ResponseInvitationDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("[Service] 거절하다 실패!", e);
        }

        return responseInvitationDTO;
    }

    @Override
    @Transactional
    public void deleteMember(RequestDeleteMemberDTO requestDeleteMemberDTO) {

        /* 궁금. 모임의 LEADER인지 확인 */
        Map<String, Object> paramsR = new HashMap<>();
        paramsR.put("selectValue", "role");
        paramsR.put("gather_id", requestDeleteMemberDTO.getGatherId());
        paramsR.put("member_id", requestDeleteMemberDTO.getUserId());
        String memberRole = (String) gatherService.findMemberRoleById(paramsR);

        Map<String, Object> paramsI = new HashMap<>();
        paramsI.put("selectValue", "id");
        paramsI.put("gather_id", requestDeleteMemberDTO.getGatherId());
        paramsI.put("member_id", requestDeleteMemberDTO.getMemberId());
        int id = (Integer) gatherService.findMemberRoleById(paramsI);

        GatherMember foundGather = null;
        try {
            foundGather = gatherMemberRepository.findById(id).orElseThrow();
        }catch(Exception e){
            throw new RuntimeException("[Service] 해당 모임을 찾을 수 없습니다.", e);
        }

        if (GatherRole.LEADER.toString().equals(memberRole)) {
            // 삭제
            gatherMemberRepository.deleteById(id);
            try {
                modelMapper.map(foundGather, GatherMember.class);
            } catch (Exception e) {
                throw new RuntimeException("[Service] 매핑 실패!");
            }
        }
    }

    private static String getNow() {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return simpleDateFormat.format(now);
    }
}
