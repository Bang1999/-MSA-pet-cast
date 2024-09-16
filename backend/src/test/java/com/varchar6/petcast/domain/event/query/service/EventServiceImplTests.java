package com.varchar6.petcast.domain.event.query.service;

import static org.junit.jupiter.api.Assertions.*;

import com.varchar6.petcast.domain.event.command.application.dto.request.EventCreateRequestDTO;
import com.varchar6.petcast.domain.event.command.domain.aggregate.EventStatusEnum;
import com.varchar6.petcast.domain.event.command.domain.aggregate.entity.Event;
import com.varchar6.petcast.domain.event.command.domain.repository.EventRepository;
import com.varchar6.petcast.domain.event.query.dto.EventDTO;
import com.varchar6.petcast.domain.event.query.mapper.EventMapper;
import com.varchar6.petcast.domain.review.query.service.ReviewServiceImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class EventServiceImplTests {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventRepository eventRepository;

    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        eventService = new EventServiceImpl(eventMapper);
    }

    @Test
    void testFindEventByCompany() {
        Integer companyId = 1;
        Integer lastEventId = 0;

        Event event = new Event();
        event.setContent("테스트용 기존 content");
        event.setStatus(EventStatusEnum.READY);
        event.setImage("테스트용 image");
        event.setTitle("테스트용 기존 title");
        event.setCompanyId(companyId);
        event.setMemberId(1);
        eventRepository.save(event);

        List<EventDTO> events = eventService.findEventByCompany(companyId, lastEventId);

        assertNotNull(events);
        assertEquals("테스트용 기존 content", events.get(0).getContent());
        assertEquals(EventStatusEnum.READY, events.get(0).getStatus());
        assertEquals(companyId, events.get(0).getCompanyId());
        assertEquals("테스트용 image", events.get(0).getImage());
        assertEquals("테스트용 기존 title", events.get(0).getTitle());
        assertEquals(1, events.get(0).getMemberId());
    }

    @Test
    void testFindEventByCustomer() {
        Integer customerId = 1;
        Integer lastEventId = 0;

        Event event = new Event();
        event.setContent("테스트용 기존 content");
        event.setStatus(EventStatusEnum.READY);
        event.setImage("테스트용 image");
        event.setTitle("테스트용 기존 title");
        event.setCompanyId(1);
        event.setMemberId(customerId);
        eventRepository.save(event);

        List<EventDTO> events = eventService.findEventByCustomer(customerId, lastEventId);

        assertNotNull(events);
        assertEquals("테스트용 기존 content", events.get(0).getContent());
        assertEquals(EventStatusEnum.READY, events.get(0).getStatus());
        assertEquals(1, events.get(0).getCompanyId());
        assertEquals("테스트용 image", events.get(0).getImage());
        assertEquals("테스트용 기존 title", events.get(0).getTitle());
        assertEquals(customerId, events.get(0).getMemberId());
    }

    @Test
    void testFindEvent() {
        Integer eventId = 3;

        Event event = new Event();
        event.setContent("테스트용 기존 content");
        event.setStatus(EventStatusEnum.READY);
        event.setImage("테스트용 image");
        event.setTitle("테스트용 기존 title");
        event.setCompanyId(1);
        event.setMemberId(1);
        Event savedEvent = eventRepository.save(event);


        assertNotNull(savedEvent);
        assertEquals("테스트용 기존 content", savedEvent.getContent());
        assertEquals(EventStatusEnum.READY, savedEvent.getStatus());
        assertEquals(1, savedEvent.getCompanyId());
        assertEquals("테스트용 image", savedEvent.getImage());
        assertEquals("테스트용 기존 title", savedEvent.getTitle());
        assertEquals(1, savedEvent.getMemberId());
    }
}
