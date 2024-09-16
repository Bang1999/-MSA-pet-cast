package com.varchar6.petcast.domain.member.query.vo;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
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
    private List<RoleVO> roleVOList;
}
