package com.varchar6.petcast.serviceothers.domain.sms.controller;

import com.varchar6.petcast.serviceothers.common.response.ResponseMessage;
import com.varchar6.petcast.serviceothers.domain.sms.dto.request.RequestSendSms;
import com.varchar6.petcast.serviceothers.domain.sms.dto.response.ResponseSendSms;
import com.varchar6.petcast.serviceothers.domain.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "smsController")
@RequestMapping("/api/v1/sms")
public class SmsController {

    private final SmsService smsService;

    @Autowired
    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseMessage> sendSms(@RequestBody RequestSendSms requestSendSms
            , @RequestHeader("X-Member-Id") String id){
        requestSendSms.setUserId(Integer.parseInt(id));
        ResponseSendSms responseSendSms = smsService.sendInvitation(requestSendSms);

        return ResponseEntity.ok(
                ResponseMessage.builder()
                        .message("메시지 전송 성공!")
                        .httpStatus(HttpStatus.OK.value())
                        .result(responseSendSms)
                        .build()
        );
    }
}
