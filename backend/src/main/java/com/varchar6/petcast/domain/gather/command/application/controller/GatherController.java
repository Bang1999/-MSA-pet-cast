package com.varchar6.petcast.domain.gather.command.application.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.gather.command.application.dto.request.*;
import com.varchar6.petcast.domain.gather.command.application.dto.response.*;
import com.varchar6.petcast.domain.gather.command.application.service.GatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value="commandGatherController")
@RequestMapping("/api/v1/gather")
@Slf4j
public class GatherController {

    private final GatherService gatherService;

    @Autowired
    public GatherController(GatherService gatherService) {
        this.gatherService = gatherService;
    }

    @PostMapping("/gather")
    public ResponseEntity<ResponseMessage> createGather(@RequestBody RequestCreateGatherDTO requestCreateGatherDTO,
                                                        @RequestAttribute("memberId") int memberId){
        requestCreateGatherDTO.setUserId(memberId);
        gatherService.createGather(requestCreateGatherDTO);

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.CREATED.value())
                        .message("모임 생성 성공!")
                        .build()
        );
    }

    @PutMapping("/gather-info")
    public ResponseEntity<ResponseMessage> updateGatherInfo(@RequestBody RequestUpdateGatherInfoDTO requestUpdateGatherDTO,
                                                            @RequestAttribute("memberId") int memberId){
        requestUpdateGatherDTO.setUserId(memberId);
        ResponseUpdateGatherInfoDTO responseUpdateGatherInfoDTO = gatherService.updateGatherInfo(requestUpdateGatherDTO);

        ResponseMessage responseMessage = ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .result(responseUpdateGatherInfoDTO)
                .build();

        if(responseUpdateGatherInfoDTO == null){
            responseMessage.setMessage("모임 정보 수정 실패!");
        }else{
            responseMessage.setMessage("모임 정보 수정 성공!");
        }

        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/deactive-gather")
    public ResponseEntity<ResponseMessage> deactiveGather(@RequestBody RequestDeactiveGatherDTO requestDeactiveGatherDTO,
                                                          @RequestAttribute("memberId") int memberId){
        requestDeactiveGatherDTO.setUserId(memberId);
        ResponseDeactiveGatherDTO responseDeactiveGatherDTO = gatherService.deactiveGather(requestDeactiveGatherDTO);

        ResponseMessage responseMessage = ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .result(responseDeactiveGatherDTO)
                .build();

        if(responseDeactiveGatherDTO == null){
            responseMessage.setMessage("모임 비활성화 실패!");
        }else{
            responseMessage.setMessage("모임 비활성화 성공!");
        }

        return ResponseEntity.ok(responseMessage);
    }

    @PostMapping("/invitation")
    public ResponseEntity<ResponseMessage> sendInvitation(@RequestBody RequestSendInvitationDTO requestSendInvitationDTO,
                                                          @RequestAttribute("memberId") int memberId) {
        requestSendInvitationDTO.setUserId(memberId);
        ResponseSendInvitaionDTO responseSendInvitaionDTO = gatherService.sendInvitation(requestSendInvitationDTO);

        ResponseMessage responseMessage = ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .result(responseSendInvitaionDTO)
                .build();

        if(responseSendInvitaionDTO == null){
            responseMessage.setMessage("초대장 전송 실패~");
        }else{
            responseMessage.setMessage("초대장 전송 성공~");
        }

        return ResponseEntity.ok(responseMessage);
    }

    @PutMapping("/invitation/accept")
    public ResponseEntity<ResponseMessage> acceptInvitation(@RequestBody RequestInvitationDTO requestInvitationDTO,
                                                            @RequestAttribute("memberId") int memberId){
        requestInvitationDTO.setUserId(memberId);
        ResponseInvitationDTO responseInvitationDTO = gatherService.acceptInvatation(requestInvitationDTO);

        ResponseMessage responseMessage = ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .result(responseInvitationDTO)
                .build();

        if(responseInvitationDTO == null){
            responseMessage.setMessage("수락 실패!");
        }else{
            responseMessage.setMessage("수락 성공!");
        }

        return ResponseEntity.ok(responseMessage);
    }

    @PutMapping("/invitation/refuse")
    public ResponseEntity<ResponseMessage> refuseInvitation(@RequestBody RequestInvitationDTO requestInvitationDTO,
                                                            @RequestAttribute("memberId") int memberId){
        requestInvitationDTO.setUserId(memberId);
        ResponseInvitationDTO responseInvitationDTO = gatherService.refuseInvatation(requestInvitationDTO);

        ResponseMessage responseMessage = ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .result(responseInvitationDTO)
                .build();

        if(responseInvitationDTO == null){
            responseMessage.setMessage("거절 실패!");
        }else{
            responseMessage.setMessage("거절 성공!");
        }

        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/member")
    public ResponseEntity<ResponseMessage> deleteMember(@RequestBody RequestDeleteMemberDTO requestDeleteMemberDTO,
                                                        @RequestAttribute("memberId") int userId){
        requestDeleteMemberDTO.setUserId(userId);
        gatherService.deleteMember(requestDeleteMemberDTO);

        return ResponseEntity.ok(
                ResponseMessage.builder().message("삭제 성공~").build()
        );
    }

}
