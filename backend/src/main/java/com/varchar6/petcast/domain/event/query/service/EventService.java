package com.varchar6.petcast.domain.event.query.service;

import com.varchar6.petcast.domain.event.query.dto.EventDTO;
import java.util.List;

public interface EventService {
    List<EventDTO> findEventByCompany(Integer companyId, Integer lastEventId);
    List<EventDTO> findEventByCustomer(Integer customerId, Integer lastEventId);
    EventDTO findEvent(Integer eventId);
}
