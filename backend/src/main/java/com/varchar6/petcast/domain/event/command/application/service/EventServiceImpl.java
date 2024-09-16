package com.varchar6.petcast.domain.event.command.application.service;

import com.varchar6.petcast.domain.event.command.application.dto.request.EventCreateRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.request.EventSetStatusRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.request.EventUpdateRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.response.EventResponseDTO;
import com.varchar6.petcast.domain.event.command.domain.aggregate.entity.Event;
import com.varchar6.petcast.domain.event.command.domain.aggregate.entity.EventCategory;
import com.varchar6.petcast.domain.event.command.domain.repository.EventCategoryRepository;
import com.varchar6.petcast.domain.event.command.domain.repository.EventRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service(value = "commandEventService")
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final EventCategoryRepository eventCategoryRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper, EventCategoryRepository eventCategoryRepository) {
        this.eventRepository = eventRepository;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
        this.eventCategoryRepository = eventCategoryRepository;
    }

    @Override
    @Transactional
    public EventResponseDTO updateEvent(EventUpdateRequestDTO updateRequestDTO) {
        Event event = eventRepository.findById(updateRequestDTO.getId()).orElseThrow();
        event.setContent(updateRequestDTO.getContent());

        EventResponseDTO responseDTO = modelMapper.map(event, EventResponseDTO.class);
        return responseDTO;
    }

    @Override
    @Transactional
    public void insertEvent(EventCreateRequestDTO eventCreateRequestDTO) {
        Event event = modelMapper.map(eventCreateRequestDTO, Event.class);

        Event savedEvent = eventRepository.save(event);

        // 저장된 Event의 ID와 categorys를 사용하여 EventCategory 저장
        saveEventCategories(savedEvent.getId(), eventCreateRequestDTO.getCategoryIds());

    }

    @Override
    @Transactional
    public EventResponseDTO setEventStatus(EventSetStatusRequestDTO eventSetStatusRequestDTO) {
        Event event = eventRepository.findById(eventSetStatusRequestDTO.getId()).orElseThrow();
        event.setStatus(eventSetStatusRequestDTO.getStatus());
        EventResponseDTO responseDTO = modelMapper.map(event, EventResponseDTO.class);

        return responseDTO;
    }

    private void saveEventCategories(Integer eventId, List<Integer> categoryIds) {
        // EventCategory를 저장하는 로직 구현
        for (Integer categoryId : categoryIds) {
            EventCategory eventCategory = new EventCategory();
            eventCategory.setEventId(eventId);
            eventCategory.setCategoryId(categoryId);
            eventCategoryRepository.save(eventCategory);
        }
    }

}
