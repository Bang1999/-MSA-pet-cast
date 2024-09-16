package com.varchar6.petcast.domain.gather.query.service;

import com.varchar6.petcast.domain.gather.query.dto.GatherDetailDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GatherServiceImplTests {

    private final GatherService gatherService;

    @Autowired
    public GatherServiceImplTests(GatherService gatherService) {
        this.gatherService = gatherService;
    }

    private static Stream<Arguments> providerSource1(){
        return Stream.of(
                Arguments.of(1, new String[]{"특수 반려동물 모임", "강아지 훈련 기본 강좌", "강아지 놀이 모임"}),
                Arguments.of(2, new String[]{"반려동물 행동 워크숍", "작은 반려동물 소셜", "고양이 포옹 시간"}),
                Arguments.of(3, new String[]{"수족관 애호가 모임", "조류 애호가 모임"})
        );
    }

    @DisplayName("목록 조회 테스트")
    @ParameterizedTest
    @MethodSource("providerSource1")
    void findListById(int userId, String[] expectedNames){

        // given
        // when
        List<String> result = gatherService.findAllGather(userId);

        // then
        assertEquals(result, Arrays.stream(expectedNames).toList());
    }






    private static Stream<Arguments> providerSource2(){
        return Stream.of(
                Arguments.of(1, new GatherDetailDTO(
                        1,
                        "강아지 놀이 모임",
                        "다른 강아지 주인들과 함께 공원에서 즐거운 하루를 보내세요. 장난감과 간식이 제공됩니다!",
                        20,
                        "https://example.com/puppy_playdate",
                        "2024-08-26T10:00:00",
                        "2024-08-26T10:00:00",
                        true,
                        List.of("김철수")
                )),
                Arguments.of(2, new GatherDetailDTO(
                        2,
                        "고양이 포옹 시간",
                        "고양이를 데리고 오셔서 편안한 오후를 보내며 교류하세요.",
                        15,
                        "https://example.com/cat_cuddle_session",
                        "2024-08-26T10:01:00",
                        "2024-08-26T10:01:00",
                        false,
                        List.of("이영희")
                )),
                Arguments.of(3, new GatherDetailDTO(
                        3,
                        "강아지 훈련 기본 강좌",
                        "강아지의 기본 복종 훈련 기술을 배워보세요.",
                        25,
                        null,
                        "2024-08-26T10:02:00",
                        "2024-08-26T10:02:00",
                        true,
                        List.of("김철수")
                ))
        );
    }
    @DisplayName("모임 정보")
    @ParameterizedTest
    @MethodSource("providerSource2")
    void findGatherDetailById(int gatherId, GatherDetailDTO expectedGatherDetail){
        // given
        // when
        GatherDetailDTO result = gatherService.findDetailGather(gatherId);

        System.out.println("result = " + result);
        System.out.println("expectedGatherDetail = " + expectedGatherDetail);
        // then
//        assertEquals(result, expectedGatherDetail);
        assertEquals(result.getId(), expectedGatherDetail.getId());
        assertEquals(result.getName(), expectedGatherDetail.getName());
        assertEquals(result.getContent(), expectedGatherDetail.getContent());
        assertEquals(result.getNumber(), expectedGatherDetail.getNumber());
        assertEquals(result.getUrl(), expectedGatherDetail.getUrl());
        assertEquals(result.getUpdatedAt(), expectedGatherDetail.getUpdatedAt());
        assertEquals(result.getCreatedAt(), expectedGatherDetail.getCreatedAt());
        assertEquals(result.isActive(), expectedGatherDetail.isActive());
        assertEquals(result.getMembers(), expectedGatherDetail.getMembers());
    }




    private static Stream<Arguments> providerSource3(){
        return Stream.of(
                Arguments.of(new int[]{1, 2}, false),
                Arguments.of(new int[]{2, 2}, true),
                Arguments.of(new int[]{6, 12}, false)
        );
    }
    @DisplayName("초대장 페이지 보여주기")
    @ParameterizedTest
    @MethodSource("providerSource3")
    void isAccessTrueGather(int[] id, Boolean expectedResult){
        // given
        // when
        Boolean result = gatherService.isAccessTrueGather(id[0], id[1]);

        // then
        assertEquals(result, expectedResult);
    }

    private static Stream<Arguments> providerSource4(){
        return Stream.of(
                Arguments.of(1, new String[]{"김철수"}),
                Arguments.of(2, new String[]{"이영희"}),
                Arguments.of(6, new String[]{"박민수"})
        );
    }
    @DisplayName("모임원 목록 조회")
    @ParameterizedTest
    @MethodSource("providerSource4")
    void findGroupMemberById(int gatherId, String[] expectedNames){
        // given
        // when
        List<String> groupMembers = gatherService.findGroupMemberById(gatherId);

        // then
        assertEquals(groupMembers, Arrays.stream(expectedNames).toList());
    }

}