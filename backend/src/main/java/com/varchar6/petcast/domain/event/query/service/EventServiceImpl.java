package com.varchar6.petcast.domain.event.query.service;

import com.varchar6.petcast.domain.event.query.dto.EventDTO;
import com.varchar6.petcast.domain.event.query.mapper.EventMapper;
import com.varchar6.petcast.domain.notice.query.dto.NoticeDTO;
import com.varchar6.petcast.domain.notice.query.mapper.NoticeMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service(value="queryEventService")
public class EventServiceImpl implements EventService{
    private final EventMapper eventMapper;

    @Autowired
    public EventServiceImpl(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Override
    @Transactional
    public List<EventDTO> findEventByCompany(Integer companyId, Integer lastEventId) {
        Map<String, String> params = new HashMap<String, String>();

        if(lastEventId == null || lastEventId == 0)
            lastEventId = Integer.MAX_VALUE;

        params.put("companyId", String.valueOf(companyId));
        params.put("lastEventId", String.valueOf(lastEventId));

        return eventMapper.selectEventByCompanyId(params);
    }

    @Override
    @Transactional
    public List<EventDTO> findEventByCustomer(Integer customerId, Integer lastEventId) {
        Map<String, String> params = new HashMap<String, String>();

        if(lastEventId == null || lastEventId == 0)
            lastEventId = Integer.MAX_VALUE;

        params.put("customerId", String.valueOf(customerId));
        params.put("lastEventId", String.valueOf(lastEventId));

        return eventMapper.selectEventByCustomerId(params);
    }

    @Override
    @Transactional
    public EventDTO findEvent(Integer eventId) {

        return eventMapper.selectEventById(eventId);
    }
}
