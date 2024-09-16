package com.varchar6.petcast.domain.notice.command.application.service;

import com.varchar6.petcast.domain.notice.command.application.dto.request.NoticeUpdateRequestDTO;
import com.varchar6.petcast.domain.notice.command.application.dto.request.NoticeWriteRequestDTO;
import com.varchar6.petcast.domain.notice.command.application.dto.response.NoticeResponseDTO;
import com.varchar6.petcast.domain.notice.command.domain.aggregate.Notice;
import com.varchar6.petcast.domain.notice.command.domain.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service(value = "commandNoticeService")
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    public NoticeServiceImpl(NoticeRepository noticeRepository, ModelMapper modelMapper) {
        this.noticeRepository = noticeRepository;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
    public int insertNotice(NoticeWriteRequestDTO noticeWriteRequestDTO) {
        int result = 0;

        Notice notice = modelMapper.map(noticeWriteRequestDTO, Notice.class);
        notice.setCreatedAt(LocalDateTime.now().format(FORMATTER));
        notice.setUpdatedAt(LocalDateTime.now().format(FORMATTER));
        notice.setView(0);
        notice.setActive(true);

        try {
            noticeRepository.save(notice);
            result++;
        }catch(Exception e){
            throw new RuntimeException("공지 입력 실패") {
            };
        }

        return result;
    }

    @Override
    @Transactional
    public NoticeResponseDTO updateNotice(NoticeUpdateRequestDTO noticeUpdateRequestDTO) {
        Notice notice = noticeRepository.findById(noticeUpdateRequestDTO.getId()).orElse(null);

        if(notice.isFixed())
            notice.setFixed(false);
        else
            notice.setFixed(true);

        notice.setUpdatedAt(LocalDateTime.now().format(FORMATTER));

        NoticeResponseDTO noticeResponseDTO = modelMapper.map(notice, NoticeResponseDTO.class);

        return noticeResponseDTO;
    }

    @Override
    @Transactional
    public int deleteNotice(int noticeId) {
        int result = 0;

        try {
            noticeRepository.deleteById(noticeId);
            result++;
        } catch (Exception e) {
            throw new RuntimeException("공지 삭제 실패") {
            };
        }

        return result;
    }

}
