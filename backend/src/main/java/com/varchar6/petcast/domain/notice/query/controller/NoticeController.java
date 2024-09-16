package com.varchar6.petcast.domain.notice.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.notice.query.dto.NoticeDTO;
import com.varchar6.petcast.domain.notice.query.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController(value = "queryNoticeController")
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/post")
    private ResponseEntity<ResponseMessage> getAllNotices(){
        List<NoticeDTO> responseNotices = noticeService.getAllNotices();

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("모든 공지 조회 성공")
                                .result(responseNotices)
                                .build()
                );

    }

    @GetMapping("/{NoticeId}")
    private ResponseEntity<ResponseMessage> getNoticeById(@PathVariable Integer NoticeId){

        NoticeDTO responseNotice = noticeService.getNoticeById(NoticeId);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("공지 ID로 조회 성공")
                                .result(responseNotice)
                                .build()
                );
    }
}
