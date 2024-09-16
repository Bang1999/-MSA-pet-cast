package com.varchar6.petcast.domain.member.query.dto;

import com.varchar6.petcast.domain.member.query.vo.RoleVO;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberLoginDTO {
    private int id;
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private String image;
    private String createdAt;
    private String updatedAt;
    private boolean active;
    private String introduction;
    private List<RoleVO> roleVOS;
}
