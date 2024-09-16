package com.varchar6.petcast.domain.event.command.application.service;

import com.varchar6.petcast.domain.event.command.application.dto.request.EventCreateRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.request.EventSetStatusRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.request.EventUpdateRequestDTO;
import com.varchar6.petcast.domain.event.command.application.dto.response.EventResponseDTO;


public interface EventService {

    EventResponseDTO updateEvent(EventUpdateRequestDTO updateRequestDTO);
    void insertEvent(EventCreateRequestDTO eventCreateRequestDTO);
    EventResponseDTO setEventStatus(EventSetStatusRequestDTO eventSetStatusRequestDTO);
}
