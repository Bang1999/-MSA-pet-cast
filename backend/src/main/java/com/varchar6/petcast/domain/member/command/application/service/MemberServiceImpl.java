package com.varchar6.petcast.domain.member.command.application.service;

import com.varchar6.petcast.domain.member.command.application.dto.request.MemberUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.ProfileRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.request.ProfileUpdateRequestDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberResponseDTO;
import com.varchar6.petcast.domain.member.command.application.dto.response.MemberUpdateResponseDTO;
import com.varchar6.petcast.domain.member.command.domain.aggregate.*;
import com.varchar6.petcast.domain.member.command.domain.repository.MemberRepository;
import com.varchar6.petcast.domain.member.command.application.dto.request.MemberRequestDTO;
import com.varchar6.petcast.domain.member.command.domain.repository.PetRepository;
import com.varchar6.petcast.domain.member.command.domain.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@Service(value="commandMemberServiceImpl")
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PetRepository petRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder,
                             RoleRepository roleRepository,
                             PetRepository petRepository) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.petRepository = petRepository;
    }

    @Override
    @Transactional
    public MemberResponseDTO registerMember(MemberRequestDTO memberRequestDTO) {

        // 비밀번호 암호화
        memberRequestDTO.setPassword(bCryptPasswordEncoder.encode(memberRequestDTO.getPassword()));

        // Member 생성
        Member member = memberRepository.save(requestDTOToEntity(memberRequestDTO));

        // 회원 권한 부여
        roleRepository.save(RoleMember.builder()
                .memberId(member.getId())
                .roleId(RoleType.CUSTOMER.getRoleId())
                .build());

        return entityToResponseDTO(member);
    }

    @Override
    @Transactional
    public MemberUpdateResponseDTO updateMemberStatus(MemberUpdateRequestDTO memberUpdateRequestDTO) {

        Member member = memberRepository.findById(memberUpdateRequestDTO.getId()).orElseThrow();

        Member updateMember = Member.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .name(member.getName())
                .phone(member.getPhone())
                .nickname(member.getNickname())
                .image(member.getImage())
                .createdAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .updatedAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .active(false)
                .introduction(member.getIntroduction())
                .build();

        memberRepository.save(updateMember);

        MemberUpdateResponseDTO UpdateResponseDTO = entityToUpdateResponseDTO(updateMember);

        return UpdateResponseDTO;
    }

    @Override
    @Transactional
    public MemberUpdateResponseDTO updateMemberPwd(MemberUpdateRequestDTO memberUpdateRequestDTO) {

        String newPassword = memberUpdateRequestDTO.getPassword();
        Member member = memberRepository.findById(memberUpdateRequestDTO.getId()).orElseThrow();

        Member updateMember = Member.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .password(newPassword)
                .name(member.getName())
                .phone(member.getPhone())
                .nickname(member.getNickname())
                .image(member.getImage())
                .createdAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .updatedAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .active(true)
                .introduction(member.getIntroduction())
                .build();

        memberRepository.save(updateMember);

        MemberUpdateResponseDTO updatePwdResponseDTO = entityToUpdateResponseDTO(updateMember);

        return updatePwdResponseDTO;

    }

    @Override
    @Transactional
    public Boolean registMemberProfile(ProfileRequestDTO profileRequestDTO) {

        Member member = memberRepository.findById(profileRequestDTO.getMemberId()).orElseThrow();

        if(member != null) {
            member.setImage(profileRequestDTO.getMemberImage());
            member.setIntroduction(profileRequestDTO.getMemberIntroduction());
            memberRepository.save(member);

            Pet pet = Pet.builder()
                    .name(profileRequestDTO.getPetName())
                    .introduction(profileRequestDTO.getPetIntroduction())
                    .gender(profileRequestDTO.getPetGender())
                    .image(profileRequestDTO.getPetImage())
                    .age(profileRequestDTO.getPetAge())
                    .createdAt(LocalDateTime.now().format(FORMATTER))
                    .updatedAt(LocalDateTime.now().format(FORMATTER))
                    .active(true)
                    .memberId(profileRequestDTO.getMemberId())
                    .build();
            petRepository.save(pet);
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public Boolean updateMemberProfile(ProfileUpdateRequestDTO profileUpdateRequestDTO) {

        Member member = memberRepository.findById(profileUpdateRequestDTO.getMemberId()).orElseThrow();
        if(member != null){
            member.setNickname(profileUpdateRequestDTO.getNickname());
            member.setUpdatedAt(LocalDateTime.now().format(FORMATTER));
            memberRepository.save(member);
        } else{
            return false;
        }

        Pet pet = petRepository.findById(profileUpdateRequestDTO.getPetId()).orElseThrow();
        if(pet != null){
            pet.setAge(profileUpdateRequestDTO.getAge());
            pet.setUpdatedAt(LocalDateTime.now().format(FORMATTER));
            petRepository.save(pet);
        } else{
            return false;
        }

        return true;
    }

    public static Member requestDTOToEntity(MemberRequestDTO memberRequestDTO) {
        return Member.builder()
                .loginId(memberRequestDTO.getLoginId())
                .password(memberRequestDTO.getPassword())
                .name(memberRequestDTO.getName())
                .phone(memberRequestDTO.getPhone())
                .nickname(memberRequestDTO.getNickname())
                .image(memberRequestDTO.getImage())
                .createdAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .updatedAt(
                        LocalDateTime.now()
                                .format(FORMATTER)
                )
                .active(true)
                .introduction(memberRequestDTO.getIntroduction())
                .build();
    }

    public static MemberResponseDTO entityToResponseDTO(Member member) {
        return MemberResponseDTO.builder()
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .name(member.getName())
                .phone(member.getNickname())
                .nickname(member.getNickname())
                .image(member.getImage())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .active(member.isActive())
                .introduction(member.getIntroduction())
                .build();
    }

    public static MemberUpdateResponseDTO entityToUpdateResponseDTO(Member member) {
        return MemberUpdateResponseDTO.builder()
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .name(member.getName())
                .phone(member.getNickname())
                .nickname(member.getNickname())
                .image(member.getImage())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .active(member.isActive())
                .introduction(member.getIntroduction())
                .build();
    }
}
