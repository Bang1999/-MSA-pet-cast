package com.varchar6.petcast.domain.notice.query.service;

import com.varchar6.petcast.domain.notice.query.dto.NoticeDTO;
import com.varchar6.petcast.domain.notice.query.mapper.NoticeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service(value="queryNoticeService")
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    @Override
    @Transactional
    public NoticeDTO getNoticeById(int memberId) {
        return noticeMapper.selectNoticeByNoticeId(memberId);
    }

    @Override
    @Transactional
    public List<NoticeDTO> getAllNotices() {
        return noticeMapper.selectAllNotice();
    }

}
