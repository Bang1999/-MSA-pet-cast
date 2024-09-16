package com.varchar6.petcast.domain.notice.command.application.service;

import com.varchar6.petcast.domain.notice.command.application.dto.request.NoticeUpdateRequestDTO;
import com.varchar6.petcast.domain.notice.command.application.dto.request.NoticeWriteRequestDTO;
import com.varchar6.petcast.domain.notice.command.application.dto.response.NoticeResponseDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeServiceImplTests {

    private final ModelMapper modelMapper;
    private final NoticeService noticeService;

    private static NoticeWriteRequestDTO noticeWriteRequestDTO = new NoticeWriteRequestDTO();
    private static NoticeUpdateRequestDTO noticeUpdateRequestDTO = new NoticeUpdateRequestDTO();

    @Autowired
    public NoticeServiceImplTests(ModelMapper modelMapper, NoticeService noticeService) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
        this.noticeService = noticeService;
    }

    @Test
    @Transactional
    public void 공지_생성_테스트() {
        noticeWriteRequestDTO.setTitle("testTitle");
        noticeWriteRequestDTO.setContent("testDescription");
        noticeWriteRequestDTO.setMemberId(3);
        noticeWriteRequestDTO.setFixed(true);
        int result = noticeService.insertNotice(noticeWriteRequestDTO);

        assertEquals(1, result);
    }

    @Test
    @Transactional
    public void 공지_수정_테스트() {
        noticeUpdateRequestDTO.setId(1);
        noticeUpdateRequestDTO.setFixed(true);
        NoticeResponseDTO noticeResponseDTO = noticeService.updateNotice(noticeUpdateRequestDTO);

        assertEquals(1, noticeResponseDTO.getId());
        assertFalse(noticeResponseDTO.isFixed());

    }

    @Test
    @Transactional
    public void 공지_삭제_테스트() {
        int result = 0;
        int targetNoticeId = 4;

        result =noticeService.deleteNotice(targetNoticeId);

        assertEquals(1, result);
    }
}