package com.varchar6.petcast.serviceothers.domain.sms.service;

import com.varchar6.petcast.serviceothers.common.exception.CommonException;
import com.varchar6.petcast.serviceothers.common.exception.ErrorCode;
import com.varchar6.petcast.serviceothers.domain.sms.dto.request.RequestSendSms;
import com.varchar6.petcast.serviceothers.domain.sms.dto.response.ResponseSendSms;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "smsServiceImpl")
//@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    @Value("${spring.sms.api-key}")
    private String apiKey;

    @Value("${spring.sms.api-secret}")
    private String apiSecret;

    @Value("${spring.sms.provider}")
    private String smsProvider;

    @Value("${spring.sms.sender}")
    private String smsSender;

    private DefaultMessageService messageService;

    // spring에서 모든 의존성 주입이 완료된 후에 실행되는 메소드를 정의하는 어노테이션
    @PostConstruct
    public void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(
                this.apiKey,
                this.apiSecret,
                this.smsProvider
        );
    }

    @Override
    public ResponseSendSms sendInvitation(RequestSendSms requestSendSms) {

        Message message = new Message(); // 생성자를 통해 API 키와 API 시크릿 전달

        // 발신 번호
        message.setFrom(this.smsSender);
        // 수신 번호
        message.setTo(requestSendSms.getNumber());
        // 메시지 내용
        message.setText(
                // 초대장 제목
                "[초대장 " + requestSendSms.getGatherId() + "]"
                        + requestSendSms.getTitle()
                        + "\n" +
                // 초대장 내용
                "[내용]" + requestSendSms.getDescription()
        );

        SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));

        if(response == null){
            throw new CommonException(ErrorCode.INVITATION_SEND_FAILURE);
        }

        return ResponseSendSms.builder()
                .messageId(response.getMessageId())
                .country(response.getCountry())
                .from(response.getFrom())
                .to(response.getTo())
                .statusCode(response.getStatusCode())
                .build();
    }
}
