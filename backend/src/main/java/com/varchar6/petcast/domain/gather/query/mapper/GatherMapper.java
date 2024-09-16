package com.varchar6.petcast.domain.gather.query.mapper;

import com.varchar6.petcast.domain.gather.query.dto.GatherDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface GatherMapper {
    List<GatherDTO> selectGatherById(int userId);

    GatherDTO selectGatherDetailById(int gatherId);

    List<String> selectMembersById(int gatherId);

    List<Integer> selectGroupMembersIdById(@Param("invitation_id")int invitationId, @Param("user_id") int userId);

    List<String> selectGroupMembersNameById(int gatherId);

    Object selectMemberRoleById(Map<String, Object> params);
}
