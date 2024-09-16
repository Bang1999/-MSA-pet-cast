package com.varchar6.petcast.domain.event.command.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.varchar6.petcast.domain.event.command.application.dto.request.EventCreateRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.request.EventSetStatusRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.request.EventUpdateRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.response.EventResponseDTO;
import com.varchar6.petcast.domain.event.command.domain.aggregate.EventCategoryId;
import com.varchar6.petcast.domain.event.command.domain.aggregate.entity.Event;
import com.varchar6.petcast.domain.event.command.domain.aggregate.entity.EventCategory;
import com.varchar6.petcast.domain.event.command.domain.aggregate.EventStatusEnum;
import com.varchar6.petcast.domain.event.command.domain.repository.EventCategoryRepository;
import com.varchar6.petcast.domain.event.command.domain.repository.EventRepository;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class EventServiceImplTests {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        eventService = new EventServiceImpl(eventRepository, modelMapper, eventCategoryRepository);
    }

    @Test
    void testInsertEvent() {
        List<Integer> categoryIds = List.of(1, 2, 3);

        EventCreateRequestDTO createRequestDTO = new EventCreateRequestDTO();
        createRequestDTO.setContent("테스트용 content");
        createRequestDTO.setStatus(EventStatusEnum.READY);
        createRequestDTO.setImage("테스트용 image");
        createRequestDTO.setTitle("테스트용 title");
        createRequestDTO.setCompanyId(1);
        createRequestDTO.setMemberId(1);
        createRequestDTO.setCategoryIds(categoryIds);

        eventService.insertEvent(createRequestDTO);

        // 실제로 저장된 이벤트를 확인
        Event savedEvent = eventRepository.findLatestEvent();
        assertNotNull(savedEvent);
        assertEquals(EventStatusEnum.READY, savedEvent.getStatus());
        assertEquals("테스트용 content", savedEvent.getContent());
        assertEquals("테스트용 image", savedEvent.getImage());
        assertEquals("테스트용 title", savedEvent.getTitle());
        assertEquals(1, savedEvent.getCompanyId());
        assertEquals(1, savedEvent.getMemberId());

        // 저장된 Event의 ID와 categorys를 사용하여 EventCategory 저장 여부 확인
        EventCategoryId id = new EventCategoryId(categoryIds.get(0), savedEvent.getId());

        EventCategory eventCategory = eventCategoryRepository.findById(id).orElse(null);
        assertNotNull(eventCategory);
    }

    @Test
    void testUpdateEvent() {
        Event event = new Event();
        event.setContent("테스트용 기존 content");
        event.setStatus(EventStatusEnum.READY);
        event.setImage("테스트용 image");
        event.setTitle("테스트용 기존 title");
        event.setCompanyId(1);
        event.setMemberId(1);
        Event savedEvent = eventRepository.save(event);

        EventUpdateRequestDTO updateRequestDTO = new EventUpdateRequestDTO();
        updateRequestDTO.setId(savedEvent.getId());
        updateRequestDTO.setContent("테스트용 수정된 content");

        EventResponseDTO responseDTO = eventService.updateEvent(updateRequestDTO);

        // 업데이트된 내용 확인
        assertNotNull(responseDTO);
        assertEquals("테스트용 수정된 content", responseDTO.getContent());
    }

    @Test
    void testSetEventStatus() {
        Event event = new Event();
        event.setContent("테스트용 기존 content");
        event.setStatus(EventStatusEnum.READY);
        event.setImage("테스트용 image");
        event.setTitle("테스트용 기존 title");
        event.setCompanyId(1);
        event.setMemberId(1);
        Event savedEvent = eventRepository.save(event);

        EventSetStatusRequestDTO setStatusRequestDTO = new EventSetStatusRequestDTO();
        setStatusRequestDTO.setId(savedEvent.getId());
        setStatusRequestDTO.setStatus(EventStatusEnum.DONE);

        EventResponseDTO responseDTO = eventService.setEventStatus(setStatusRequestDTO);

        // 상태 변경 확인
        assertNotNull(responseDTO);
        assertEquals(EventStatusEnum.DONE, responseDTO.getStatus());

    }
}