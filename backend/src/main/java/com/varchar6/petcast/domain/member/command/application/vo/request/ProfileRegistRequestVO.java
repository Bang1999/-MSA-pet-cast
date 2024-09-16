package com.varchar6.petcast.domain.member.command.application.vo.request;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Gender;
import lombok.Data;

@Data
public class ProfileRegistRequestVO {
    private int memberId;
    private String memberIntroduction;
    private String memberImage;

    private String petName;
    private String petIntroduction;
    private Gender petGender;
    private String petImage;
    private int petAge;
}
