package com.varchar6.petcast.domain.member.query.mapper;

import com.varchar6.petcast.domain.member.query.dto.MemberDTO;
import com.varchar6.petcast.domain.member.query.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

@Mapper
public interface MemberMapper {

    MemberDTO selectMemberByMemberId(int id);

    MemberVO selectMemberByMemberIdWithAuthority(String memberId);

    MemberDTO checkDoubleByLoginId(String loginId);

    MemberDTO checkDoubleByNickName(String nickName);

    MemberDTO searchLoginIdByNameAndPhone(Map<String, String> params);

    MemberDTO checkIdAndPhone(Map<String, Object> resultMap);

    MemberDTO checkPasswordByIdAndPassword(int id);

    MemberVO searchMemberRole(int id);
}
