
package com.varchar6.petcast.domain.member.query.service;

import com.varchar6.petcast.domain.member.query.vo.RoleVO;
import io.jsonwebtoken.lang.Assert;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

@SpringBootTest
public class memberServiceTests {

    @Autowired
    private MemberService memberService;


    @Test
    public void 멤버아이디로_멤버_조회() {
        int input_number = 1;

        int memberId = memberService.getMemberInformationById(input_number).getId();
        Assertions.assertTrue((input_number == memberId));
    }

    @Test
    @Transactional
    public void 아이디_중복_체크() {
        String inputLoginId = "user1111";

        Boolean result = memberService.checkDoubleByLoginId(inputLoginId);
        Assertions.assertTrue(result);
    }

    @Test
    @Transactional
    public void 닉네임_중복_체크() {
        String inputNickName = "사람";

        Boolean result = memberService.checkDoubleByNickName(inputNickName);
        Assertions.assertTrue(result);
    }

    @Test
    @Transactional
    public void 아이디_찾기(){
        String inputName = "김철수";
        String inputPhone = "010-1234-5678";

        String loginId = memberService.searchLoginIdByNameAndPhone(inputName,inputPhone);
        Assertions.assertEquals("user01",loginId);
    }

    @Test
    @Transactional
    public void 아이디_전화번호_체크() {
        String inputLoginId = "user01";
        String inputPhone = "010-1234-5678";

        int searchId = memberService.checkIdAndPhone(inputLoginId,inputPhone);
        Assertions.assertEquals(1,searchId);
    }

    @Test
    @Transactional
    public void 비밀_번호_체크() {
        int inputId =  37;
        String inputPw = "1111";

        String password = memberService.checkPasswordByIdAndPassword(inputId);
        Assertions.assertEquals(inputPw,password);
    }

    @Test
    @Transactional
    public void 멤버_권한_체크() {
        int inputId = 1;

        List<RoleVO> result = memberService.searchMemberRole(inputId);

        List<RoleVO> expected = Arrays.asList(
                new RoleVO("ROLE_ADMIN", true),
                new RoleVO("ROLE_CUSTOMER", true)
        );

        Assertions.assertIterableEquals(expected, result);
    }
}
