package com.varchar6.petcast.domain.event.query.dto;

import com.varchar6.petcast.domain.event.command.domain.aggregate.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private int id;
    private String title;
    private String content;
    private String image;
    private EventStatusEnum status;
    private Integer companyId;
    private Integer memberId;

}
