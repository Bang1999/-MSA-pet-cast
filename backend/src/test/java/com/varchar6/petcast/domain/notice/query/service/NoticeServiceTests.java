package com.varchar6.petcast.domain.notice.query.service;

import com.varchar6.petcast.domain.notice.query.dto.NoticeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeServiceTests {
    @Autowired
    private NoticeService noticeService;

    @Test
    public void 공지_코드로_공지_찾기() {
        int targetNoticeId = 1;
        NoticeDTO noticeDTO = noticeService.getNoticeById(targetNoticeId);

        assertEquals(noticeDTO.getId(), targetNoticeId);
    }

    @Test
    public void 모든_공지_조회() {
        List<NoticeDTO> noticeResponseDTOList = noticeService.getAllNotices();
        assertEquals(10, noticeResponseDTOList.size());
    }

}