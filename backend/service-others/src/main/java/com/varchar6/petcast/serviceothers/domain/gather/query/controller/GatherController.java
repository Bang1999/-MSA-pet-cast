package com.varchar6.petcast.serviceothers.domain.gather.query.controller;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.gather.query.dto.GatherDetailDTO;
import com.varchar6.petcast.serviceothers.domain.gather.query.service.GatherService;
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

    // 모임 목록 조회
    @GetMapping("/grouplist")
    public ResponseEntity<ResponseMessage> findAllGather(@RequestHeader("X-Member-Id") String id) {
        List<String> gathers = gatherService.findAllGather(Integer.parseInt(id));

        ResponseMessage responseMessage = ResponseMessage.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("모임 목록 조회 성공!")
                .result(gathers)
                .build();

        return ResponseEntity.ok(responseMessage);
    }

    // 모임 상세 조회
    @GetMapping("/detail/{gatherId}")
    public ResponseEntity<ResponseMessage> findDetailGather(@PathVariable("gatherId") int gatherId) {
        GatherDetailDTO gatherDetail = gatherService.findDetailGather(gatherId);

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("모임 상세 조회 성공!")
                        .result(gatherDetail)
                        .build()
        );
    }

    // 초대장 페이지 보여주기
    @GetMapping("/invitation/{invitationId}")
    public ResponseEntity<ResponseMessage> findInvitationPageById(@PathVariable("invitationId") int invitationId,
                                                                  @RequestHeader("X-Member-Id") String id) {
        Boolean isAccessTrueGather = gatherService.isAccessTrueGather(invitationId, Integer.parseInt(id));

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("모임 접근 가능!")
                        .result(isAccessTrueGather)
                        .build()
        );
    }

    // 모임원 목록 조회
    @GetMapping("groupmemberlist/{gatherId}")
    public ResponseEntity<ResponseMessage> findGroupMemberById(@PathVariable("gatherId") int gatherId) {
        List<String> groupMembers = gatherService.findGroupMemberById(gatherId);

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message("모임원 목록 조회 성공!")
                        .result(groupMembers)
                        .build()
        );
    }

}
