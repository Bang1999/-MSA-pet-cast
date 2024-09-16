package com.varchar6.petcast.domain.member.query.service;

import com.varchar6.petcast.domain.member.query.dto.MemberDTO;
import com.varchar6.petcast.domain.member.query.mapper.MemberMapper;
import com.varchar6.petcast.domain.member.query.vo.MemberVO;
import com.varchar6.petcast.domain.member.query.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service(value="queryMemberService")
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public MemberDTO getMemberInformationById(int memberId) {
        return memberMapper.selectMemberByMemberId(memberId);
    }

    @Override
    public Boolean checkDoubleByLoginId(String loginId) {

        MemberDTO memberDTO = memberMapper.checkDoubleByLoginId(loginId);

        if (memberDTO == null || memberDTO.getLoginId() == null) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean checkDoubleByNickName(String nickName) {

        MemberDTO memberDTO = memberMapper.checkDoubleByNickName(nickName);

        if(memberDTO == null || memberDTO.getNickname() == null) {return true;}

        return false;
    }

    @Override
    public String searchLoginIdByNameAndPhone(String name, String phone) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("phone", phone);
        MemberDTO memberDTO = memberMapper.searchLoginIdByNameAndPhone(params);

        if(memberDTO != null && memberDTO.getLoginId() != null) {
            return memberDTO.getLoginId();
        } else {
            return null;
        }
    }

    @Override
    public String checkPasswordByIdAndPassword(int id) {

        MemberDTO memberDTO = memberMapper.checkPasswordByIdAndPassword(id);

        if(memberDTO != null) {
            return memberDTO.getPassword();
        } else{
            return null;
        }
    }

    @Override
    public int checkIdAndPhone(String loginId, String phone) {

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("loginId", loginId);
        resultMap.put("phone", phone);

        MemberDTO memberDTO = memberMapper.checkIdAndPhone(resultMap);

        if(memberDTO != null && memberDTO.getId() != 0){
            return memberDTO.getId();
        }

        return 0;
    }

    @Override
    public List<RoleVO> searchMemberRole(int id) {

        MemberVO memberVO  = memberMapper.searchMemberRole(id);

        if(memberVO == null){
            return null;
        }

        return memberVO.getRoleVOList();
    }


}
