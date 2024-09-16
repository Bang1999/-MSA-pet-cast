package com.varchar6.petcast.domain.member.command.application.dto.response;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateResponseDTO {

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
}
