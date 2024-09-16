package com.varchar6.petcast.domain.event.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventUpdateRequestDTO {
    private int id;
    private String content;

}
