package com.varchar6.petcast.domain.request.command.application.service;

import com.varchar6.petcast.domain.request.command.application.dto.request.CreateRequestsRequestDTO;
import com.varchar6.petcast.domain.request.command.domain.aggregate.EventsStatus;
import com.varchar6.petcast.domain.request.command.domain.aggregate.RequestsStatus;
import com.varchar6.petcast.domain.request.command.domain.aggregate.entity.Event;
import com.varchar6.petcast.domain.request.command.domain.aggregate.entity.Requests;
import com.varchar6.petcast.domain.request.command.domain.repository.EventsRepository;
import com.varchar6.petcast.domain.request.command.domain.repository.RequestsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Service(value = "commandRequestServiceImpl")
@Slf4j
public class RequestsServiceImpl implements RequestsService {
    private final RequestsRepository requestsRepository;
    private final EventsRepository eventsRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public RequestsServiceImpl(RequestsRepository requestsRepository,
                               EventsRepository eventsRepository) {
        this.requestsRepository = requestsRepository;
        this.eventsRepository = eventsRepository;
    }

    // 요청서 작성
    @Override
    @Transactional
    public void createRequest(CreateRequestsRequestDTO createRequestsRequestDTO, int memberId) {
        Requests requests = Requests.builder()
                .content(createRequestsRequestDTO.getContent())
                .hopeCost(createRequestsRequestDTO.getHopeCost())
                .hopeLocation(createRequestsRequestDTO.getHopeLocation())
                .hopeTime(createRequestsRequestDTO.getHopeTime())
                .status(RequestsStatus.SENT)
                .updatedAt(LocalDateTime.now().format(FORMATTER))
                .createdAt(LocalDateTime.now().format(FORMATTER))
                .active(true)
                .companyId(createRequestsRequestDTO.getCompanyId())
                .memberId(memberId)
                .build();

        try {
            requestsRepository.save(requests);
        } catch (Exception e) {
            log.warn("[Service] Repository에 넣다가 실패!");
        }
    }

    // 요청서 삭제
    @Override
    @Transactional
    public void deleteRequest(int requestId, int memberId) {
        Requests findRequests = requestsRepository.findById(requestId).orElseThrow(
                () -> (new NoSuchElementException("해당 요청서가 없습니다."))
        );
        findRequests.setActive(false);
        try {
            requestsRepository.save(findRequests);
        }catch (Exception e){
            throw new RuntimeException("[Service] 비활성화 DB에 업데이트 실패!", e);
        }
    }

    // 요청서 수락
    @Override
    @Transactional
    public void acceptRequest(int requestId) {
        Requests request = requestsRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + requestId + " 번 요청서를 찾을 수 없습니다."));

        request.setStatus(RequestsStatus.CONFIRMED);    // 상태 변경
        try {
            requestsRepository.save(request);  // 상태 업데이트 저장
        }catch (Exception e) {
            throw new RuntimeException("[Service] 요청서 수락 실패!", e);
        }

        Event newEvent = Event.builder()
                .title(request.getMemberId()+"님의 "+request.getCompanyId()+"기업 요청서")
                .content(request.getContent())
                .status(EventsStatus.READY)
                .companyId(request.getCompanyId())
                .memberId(request.getMemberId())
                .build();
        try {
            eventsRepository.save(newEvent);
        }catch (Exception e){
            throw new RuntimeException("[Service] 이벤트 생성 실패!", e);
        }
    }

    // 요청서 거절
    @Override
    @Transactional
    public void rejectRequest(int requestId) {
        Requests request = requestsRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + requestId + " 번 요청서를 찾을 수 없습니다."));

        request.setStatus(RequestsStatus.REJECTED); // 상태 변경

        try {
            request = requestsRepository.save(request);  // 상태 업데이트 저장
        }catch (Exception e) {
            throw new RuntimeException("[Service] 요청서 거절 실패!", e);
        }

    }

}
