package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.ProfileRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.ProfileUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberUpdateResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService {
    MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO);

    MemberUpdateResponseDTO updateMemberStatus(MemberUpdateRequestDTO memberUpdateRequestDTO);

    MemberUpdateResponseDTO updateMemberPwd(MemberUpdateRequestDTO memberUpdateRequestDTO);

    Boolean registMemberProfile(ProfileRequestDTO profileRequestDTO);

    Boolean updateMemberProfile(ProfileUpdateRequestDTO profileUpdateRequestDTO);
}
