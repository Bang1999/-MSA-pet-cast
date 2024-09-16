package com.varchar6.petcast.domain.member.command.application.dto.request;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Gender;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequestDTO {

    private int memberId;
    private String memberIntroduction;
    private String memberImage;

    private String petName;
    private String petIntroduction;
    private Gender petGender;
    private String petImage;
    private int petAge;
}
