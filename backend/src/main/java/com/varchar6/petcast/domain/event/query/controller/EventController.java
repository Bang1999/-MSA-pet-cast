package com.varchar6.petcast.domain.event.query.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.event.command.application.dto.request.EventCreateRequestDTO;
import com.varchar6.petcast.domain.event.query.dto.EventDTO;
import com.varchar6.petcast.domain.event.query.service.EventService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController(value = "queryEventController")
@RequestMapping("/api/v1/events")
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/companies/{companyId}")
    private ResponseEntity<ResponseMessage> findEventByCompany(@PathVariable Integer companyId,
        @RequestParam Integer lastEventId){

        List<EventDTO> responseEvents = eventService.findEventByCompany(companyId, lastEventId);

        return ResponseEntity
            .ok()
            .body(
                ResponseMessage.builder()
                    .httpStatus(HttpStatus.OK.value())
                    .message("업체가 수행한 이벤트 조회 성공")
                    .result(responseEvents)
                    .build()
            );

    }

    @GetMapping("/users")
    private ResponseEntity<ResponseMessage> findEventByCustomer(@RequestAttribute("memberId") int memberId,
        @RequestParam Integer lastEventId){

        List<EventDTO> responseEvents = eventService.findEventByCustomer(memberId, lastEventId);

        return ResponseEntity
            .ok()
            .body(
                ResponseMessage.builder()
                    .httpStatus(HttpStatus.OK.value())
                    .message("고객이 요청한 이벤트 조회 성공")
                    .result(responseEvents)
                    .build()
            );
    }

    @GetMapping("/{eventId}")
    private ResponseEntity<ResponseMessage> findEvent(@PathVariable Integer eventId){

        EventDTO responseEvent = eventService.findEvent(eventId);

        return ResponseEntity
            .ok()
            .body(
                ResponseMessage.builder()
                    .httpStatus(HttpStatus.OK.value())
                    .message("이벤트 조회 성공")
                    .result(responseEvent)
                    .build()
            );
    }


}
