package com.varchar6.petcast.domain.member.command.application.dto.request;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Gender;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUpdateRequestDTO {

    private int memberId;
    private String nickname;
    private int petId;
    private int age;
    private String gender;

}
