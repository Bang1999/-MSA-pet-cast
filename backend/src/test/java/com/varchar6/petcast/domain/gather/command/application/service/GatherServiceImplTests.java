package com.varchar6.petcast.domain.gather.command.application.service;

import com.varchar6.petcast.domain.gather.command.application.dto.request.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GatherServiceImplTests {

    private final GatherService gatherService;

    @Autowired
    public GatherServiceImplTests(GatherService gatherService) {
        this.gatherService = gatherService;
    }

    private static Stream<Arguments> providerSource1() {
        return Stream.of(
                Arguments.of(new RequestCreateGatherDTO(
                        "모임 A",
                        "모임 A의 자세한 정보입니다.",
                        123,
                        "https://example.com/meeting_a",
                        true,
                        1
                )),
                Arguments.of(new RequestCreateGatherDTO(
                        "모임 B",
                        "모임 B의 자세한 정보입니다.",
                        456,
                        "https://example.com/meeting_b",
                        true,
                        6
                )),
                Arguments.of(new RequestCreateGatherDTO(
                        "모임 C",
                        "모임 C의 자세한 정보입니다.",
                        789,
                        "https://example.com/meeting_c",
                        true,
                        9
                ))
        );
    }

    @DisplayName("모임 생성")
    @ParameterizedTest
    @MethodSource("providerSource1")
    void createGather(RequestCreateGatherDTO requestCreateGatherDTO) {

        Assertions.assertDoesNotThrow(
                () -> gatherService.createGather(requestCreateGatherDTO)
        );
    }

    private static Stream<Arguments> providerSource2() {
        return Stream.of(
                Arguments.of(new RequestUpdateGatherInfoDTO(
                        4,
                        13,
                        "모임1",
                        "모임 정보 1",
                        987,
                        "www.example11.com",
                        true
                        )),
                Arguments.of(new RequestUpdateGatherInfoDTO(
                        5,
                        14,
                        "모임2",
                        "모임 정보 2",
                        496,
                        "www.example22.com",
                        true
                )),
                Arguments.of(new RequestUpdateGatherInfoDTO(
                        9,
                        30,
                        "모임3",
                        "모임 정보 3",
                        245,
                        "www.example333.com",
                        true
                ))
        );
    }

    @DisplayName("모임 정보 수정")
    @ParameterizedTest
    @MethodSource("providerSource2")
    void updateGatherInfo(RequestUpdateGatherInfoDTO updateGatherInfoDTO) {

        Assertions.assertDoesNotThrow(
                () -> gatherService.updateGatherInfo(updateGatherInfoDTO)
        );
    }

    private static Stream<Arguments> providerSource3() {
        return Stream.of(
                Arguments.of(new RequestDeactiveGatherDTO(
                        4,
                        13
                )),
                Arguments.of(new RequestDeactiveGatherDTO(
                        5,
                        14
                )),
                Arguments.of(new RequestDeactiveGatherDTO(
                        9,
                        30
                ))
        );
    }

    @DisplayName("모임 비활성화")
    @ParameterizedTest
    @MethodSource("providerSource3")
    void deactiveGather(RequestDeactiveGatherDTO requestDeactiveGatherDTO){
        Assertions.assertDoesNotThrow(
                () -> gatherService.deactiveGather(requestDeactiveGatherDTO)
        );
    }

    private static Stream<Arguments> providerSource4() {
        return Stream.of(
                Arguments.of(new RequestSendInvitationDTO(
                        4,
                        11
                )),
                Arguments.of(new RequestSendInvitationDTO(
                        5,
                        14
                )),
                Arguments.of(new RequestSendInvitationDTO(
                        9,
                        30
                ))
        );
    }

    @DisplayName("초대장 전송")
    @ParameterizedTest
    @MethodSource("providerSource4")
    void sendInvitation(RequestSendInvitationDTO requestSendInvitationDTO){
        Assertions.assertDoesNotThrow(
                () -> gatherService.sendInvitation(requestSendInvitationDTO)
        );
    }

    private static Stream<Arguments> providerSource5() {
        return Stream.of(
                Arguments.of(new RequestInvitationDTO(
                        2,
                        1
                )),
                Arguments.of(new RequestInvitationDTO(
                        2,
                        2
                )),
                Arguments.of(new RequestInvitationDTO(
                        7,
                        5
                ))
        );
    }

    @DisplayName("초대장 수락")
    @ParameterizedTest
    @MethodSource("providerSource5")
    void acceptInvatation(RequestInvitationDTO requestInvitationDTO){
        assertDoesNotThrow(
                () -> gatherService.acceptInvatation(requestInvitationDTO)
        );
    }

    private static Stream<Arguments> providerSource6() {
        return Stream.of(
                Arguments.of(new RequestInvitationDTO(
                        2,
                        1
                )),
                Arguments.of(new RequestInvitationDTO(
                        2,
                        2
                )),
                Arguments.of(new RequestInvitationDTO(
                        7,
                        5
                ))
        );
    }

    @DisplayName("초대장 거절")
    @ParameterizedTest
    @MethodSource("providerSource6")
    void refuseInvatation(RequestInvitationDTO requestInvitationDTO) {
        assertDoesNotThrow(
                () -> gatherService.refuseInvatation(requestInvitationDTO)
        );
    }

    private static Stream<Arguments> providerSource7() {
        return Stream.of(
                Arguments.of(new RequestDeleteMemberDTO(
                        1,
                        8,
                        1
                )),
                Arguments.of(new RequestDeleteMemberDTO(
                        3,
                        4,
                        2
                )),
                Arguments.of(new RequestDeleteMemberDTO(
                        5,
                        14,
                        6
                ))
        );
    }

    @DisplayName("모임원 삭제")
    @ParameterizedTest
    @MethodSource("providerSource7")
    void deleteMember(RequestDeleteMemberDTO requestDeleteMemberDTO) {
        assertDoesNotThrow(
                () -> gatherService.deleteMember(requestDeleteMemberDTO)
        );
    }
}