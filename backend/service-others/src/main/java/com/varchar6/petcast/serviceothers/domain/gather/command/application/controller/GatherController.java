package com.varchar6.petcast.serviceothers.domain.gather.command.application.controller;

import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestCreateGatherDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestDeactiveGatherDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestDeleteMemberDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestInvitationDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestSendInvitationDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request.RequestUpdateGatherInfoDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.response.ResponseDeactiveGatherDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.response.ResponseInvitationDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.response.ResponseSendInvitaionDTO;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.response.ResponseUpdateGatherInfoDTO;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.gather.command.application.service.GatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "commandGatherController")
@RequestMapping("/api/v1/gather")
@Slf4j
public class GatherController {

    private final GatherService gatherService;

    @Autowired
    public GatherController(GatherService gatherService) {
        this.gatherService = gatherService;
    }

    // 모임 생성
    @PostMapping("")
    private ResponseEntity<ResponseMessage> createGather(@RequestHeader(value = "X-Member-Id", required = false) String id,
                                                         @RequestBody RequestCreateGatherDTO requestCreateGatherDTO) {
        requestCreateGatherDTO.setUserId(Integer.parseInt(id));
        gatherService.createGather(requestCreateGatherDTO);

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.CREATED.value())
                        .message("모임 생성 성공!")
                        .build()
        );
    }

    // 모임 수정
    @PutMapping("")
    public ResponseEntity<ResponseMessage> updateGatherInfo(@RequestBody RequestUpdateGatherInfoDTO requestUpdateGatherDTO
            , @RequestHeader("X-Member-Id") String id
    ) {
        requestUpdateGatherDTO.setUserId(Integer.parseInt(id));
        ResponseUpdateGatherInfoDTO responseUpdateGatherInfoDTO = gatherService.updateGatherInfo(requestUpdateGatherDTO);

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("모임 수정 성공!")
                        .result(responseUpdateGatherInfoDTO)
                        .build());
    }

    // 모임 삭제
    @DeleteMapping("")
    public ResponseEntity<ResponseMessage> deactiveGather(@RequestBody RequestDeactiveGatherDTO requestDeactiveGatherDTO
            , @RequestHeader("X-Member-Id") String id
    ) {
        requestDeactiveGatherDTO.setUserId(Integer.parseInt(id));
        ResponseDeactiveGatherDTO responseDeactiveGatherDTO = gatherService.deactiveGather(requestDeactiveGatherDTO);


        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("모임 비활성화 성공!")
                        .result(responseDeactiveGatherDTO)
                        .build());
    }

    // 초대장 전송
    @PostMapping("/invitation")
    public ResponseEntity<ResponseMessage> sendInvitation(@RequestBody RequestSendInvitationDTO requestSendInvitationDTO,
                                                          @RequestHeader("X-Member-Id") String id) {
        requestSendInvitationDTO.setUserId(Integer.parseInt(id));
        ResponseSendInvitaionDTO responseSendInvitaionDTO = gatherService.sendInvitation(requestSendInvitationDTO);

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("초대장 전송 성공!")
                        .result(responseSendInvitaionDTO)
                        .build()
        );
    }

    // 초대장 수락
    @PutMapping("/invitation/accept")
    public ResponseEntity<ResponseMessage> acceptInvitation(@RequestBody RequestInvitationDTO requestInvitationDTO,
                                                            @RequestHeader("X-Member-Id") String id) {
        requestInvitationDTO.setUserId(Integer.parseInt(id));
        ResponseInvitationDTO responseInvitationDTO = gatherService.acceptInvatation(requestInvitationDTO);

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("수락 성공!")
                        .result(responseInvitationDTO)
                        .build()
        );
    }

    // 초대장 거절
    @PutMapping("/invitation/refuse")
    public ResponseEntity<ResponseMessage> refuseInvitation(@RequestBody RequestInvitationDTO requestInvitationDTO,
                                                            @RequestHeader("X-Member-Id") String id) {
        requestInvitationDTO.setUserId(Integer.parseInt(id));
        ResponseInvitationDTO responseInvitationDTO = gatherService.refuseInvatation(requestInvitationDTO);

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("거절 성공!")
                        .result(responseInvitationDTO)
                        .build()
        );
    }

    // 모임원 추방
    @DeleteMapping("/member")
    public ResponseEntity<ResponseMessage> deleteMember(@RequestBody RequestDeleteMemberDTO requestDeleteMemberDTO,
                                                        @RequestHeader("X-Member-Id") String id) {
        requestDeleteMemberDTO.setUserId(Integer.parseInt(id));
        gatherService.deleteMember(requestDeleteMemberDTO);

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .message("삭제 성공!")
                        .build()
        );
    }
}
