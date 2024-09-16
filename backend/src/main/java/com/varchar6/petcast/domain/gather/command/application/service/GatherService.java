package com.varchar6.petcast.domain.gather.command.application.service;

import com.varchar6.petcast.domain.gather.command.application.dto.request.*;
import com.varchar6.petcast.domain.gather.command.application.dto.response.*;

public interface GatherService {
    void createGather(RequestCreateGatherDTO createGatherDTO);

    ResponseUpdateGatherInfoDTO updateGatherInfo(RequestUpdateGatherInfoDTO requestUpdateGatherDTO);

    ResponseDeactiveGatherDTO deactiveGather(RequestDeactiveGatherDTO requestDeactiveGatherDTO);

    ResponseSendInvitaionDTO sendInvitation(RequestSendInvitationDTO requestInvitationDTO);

    ResponseInvitationDTO acceptInvatation(RequestInvitationDTO requestInvitationDTO);

    ResponseInvitationDTO refuseInvatation(RequestInvitationDTO requestInvitationDTO);

    void deleteMember(RequestDeleteMemberDTO requestDeleteMemberDTO);
}
