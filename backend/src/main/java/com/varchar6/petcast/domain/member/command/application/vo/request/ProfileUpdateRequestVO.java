package com.varchar6.petcast.domain.member.command.application.vo.request;

import lombok.Data;

@Data
public class ProfileUpdateRequestVO {

    private int memberId;
    private String nickname;
    private int petId;
    private int age;
}
