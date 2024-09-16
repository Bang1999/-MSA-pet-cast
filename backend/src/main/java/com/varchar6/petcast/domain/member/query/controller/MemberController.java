package com.varchar6.petcast.domain.member.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.member.query.service.MemberService;
import com.varchar6.petcast.domain.member.query.vo.MemberVO;
import com.varchar6.petcast.domain.member.query.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController(value = "queryMemberController")
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberController(MemberService memberService,
                            PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("{memberId}")
    public ResponseEntity<ResponseMessage> getMember(
            @PathVariable("memberId") Integer memberId
    ) {
        return ResponseEntity.ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.CREATED.value())
                                .message("Login completed")
                                .result(memberService.getMemberInformationById(memberId))
                                .build()
                );
    }

    @PostMapping("/id-check")
    public ResponseEntity<ResponseMessage> checkDoubleByLoginId(@RequestBody MemberVO memberVO){

        String loginId = memberVO.getLoginId();

        Boolean answer = memberService.checkDoubleByLoginId(loginId);

        return ResponseEntity.ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("아이디 중복 체크 성공")
                                .result(answer)
                                .build()
                );
    }

    @PostMapping("/nickname-check")
    public ResponseEntity<ResponseMessage> checkDoubleByNickName(@RequestBody MemberVO memberVO){

        String nickName = memberVO.getNickname();

        Boolean answer = memberService.checkDoubleByNickName(nickName);

        return ResponseEntity.ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("닉네임 중복 체크 성공")
                                .result(answer)
                                .build()
                );
    }

    @GetMapping("/search-loginId")
    public ResponseEntity<ResponseMessage> searchLoginIdByNameAndPhone(@RequestBody MemberVO memberVO){

        String name = memberVO.getName();
        String phone = memberVO.getPhone();

        String memberLoginId = memberService.searchLoginIdByNameAndPhone(name, phone);

        return ResponseEntity.ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("이름, 전화번호로 로그인 아이디 찾기 성공")
                                .result(memberLoginId)
                                .build()
                );
    }

    @GetMapping("/password-change-possible")
    public ResponseEntity<ResponseMessage> checkIdAndPhone(@RequestAttribute("memberLoginId") String loginId,
                                                           @RequestAttribute("memberPhone") String phone) {

        int answer = memberService.checkIdAndPhone(loginId, phone);

        Map<String, Object> resultMap = new HashMap<>();

        if ( answer != 0) {
            resultMap.put("id", answer);
            resultMap.put("isPossible", true);
        } else {
            resultMap.put("id", null);
            resultMap.put("isPossible", false);
        }

        return ResponseEntity.ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("비밀번호 수정 여부 체크 성공")
                                .result(resultMap)
                                .build()
                );
    }

    @PostMapping("/password-check")
    public ResponseEntity<ResponseMessage> checkPasswordByIdAndPassword(@RequestBody MemberVO memberVO,
                                                                        @RequestAttribute("memberId") int id){

        String password = memberVO.getPassword();
        String answer = memberService.checkPasswordByIdAndPassword(id);

        log.info(answer);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("비밀번호 체크 성공")
                                .result(passwordEncoder.matches(password,answer))
                                .build()
                );
    }

    @GetMapping("/search-member-role")
    public ResponseEntity<ResponseMessage> searchMemberRole(@RequestAttribute("memberId") int id){


        List<RoleVO> roleList = memberService.searchMemberRole(id);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.OK.value())
                                .message("회원 권한 조회 성공")
                                .result(roleList)
                                .build()
                );
    }

}
