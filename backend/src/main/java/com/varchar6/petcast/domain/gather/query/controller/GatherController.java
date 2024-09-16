package com.varchar6.petcast.domain.gather.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import com.varchar6.petcast.domain.gather.query.dto.GatherDetailDTO;
import com.varchar6.petcast.domain.gather.query.service.GatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "queryGatherController")
@RequestMapping("/api/v1/gather")
public class GatherController {

    private final GatherService gatherService;

    @Autowired
    public GatherController(GatherService gatherService) {
        this.gatherService = gatherService;
    }

    @GetMapping("/grouplist")
    public ResponseEntity<ResponseMessage> findAllGather(@RequestAttribute("memberId") int memberId){
        List<String> gathers = gatherService.findAllGather(memberId);

        ResponseMessage responseMessage = ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("모임 목록 조회 성공!")
                .result(gathers)
                .build();

        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/detail/{gatherId}")
    public ResponseEntity<ResponseMessage> findDetailGather(@PathVariable("gatherId") int gatherId){
        GatherDetailDTO gatherDetail = gatherService.findDetailGather(gatherId);

        ResponseMessage responseMessage = ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("모임 상세 조회 성공!")
                .result(gatherDetail)
                .build();

        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/invitation")
    public ResponseEntity<ResponseMessage> findInvitationPageById(@RequestParam("invitationId") int invitationId,
                                                                  @RequestAttribute("memberId") int memberId){
        Boolean isAccessTrueGather = gatherService.isAccessTrueGather(invitationId, memberId);

        ResponseMessage responseMessage = ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("모임 접근 가능!")
                .result(isAccessTrueGather)
                .build();

        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("groupmemberlist/{gatherId}")
    public ResponseEntity<ResponseMessage> findGroupMemberById(@PathVariable("gatherId") int gatherId){
        List<String> groupMembers = gatherService.findGroupMemberById(gatherId);

        ResponseMessage responseMessage = ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("모임원 목록 조회 성공!")
                .result(groupMembers)
                .build();

        return ResponseEntity.ok(responseMessage);
    }

}
