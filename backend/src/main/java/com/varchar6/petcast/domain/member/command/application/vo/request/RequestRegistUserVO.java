package com.varchar6.petcast.domain.member.command.application.vo.request;

import lombok.Data;

@Data
public class RequestRegistUserVO {
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private String image;
    private String introduction;
}
