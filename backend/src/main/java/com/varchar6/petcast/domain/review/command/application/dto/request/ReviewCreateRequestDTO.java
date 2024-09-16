package com.varchar6.petcast.domain.review.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateRequestDTO {
    private String title;
    private String comment;
    private int score;
    private Integer eventId;

}
