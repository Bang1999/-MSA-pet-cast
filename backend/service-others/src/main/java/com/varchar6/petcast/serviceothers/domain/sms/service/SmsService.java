package com.varchar6.petcast.serviceothers.domain.sms.service;

import com.varchar6.petcast.serviceothers.domain.sms.dto.request.RequestSendSms;
import com.varchar6.petcast.serviceothers.domain.sms.dto.response.ResponseSendSms;

public interface SmsService {
    ResponseSendSms sendInvitation(RequestSendSms requestSendSms);
}
