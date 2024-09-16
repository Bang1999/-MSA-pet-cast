package com.varchar6.petcast.domain.member.command.application.vo.response;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Gender;
import lombok.Data;

@Data
public class ProfileRegistResponseVO {
    private int memberId;
    private String memberIntroduction;
    private String memberImage;

    private String petName;
    private String petIntroduction;
    private Gender petGender;
    private String petImage;
    private int petAge;
}
