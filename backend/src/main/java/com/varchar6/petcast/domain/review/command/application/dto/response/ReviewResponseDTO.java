package com.varchar6.petcast.domain.review.command.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {
    private int id;
    private String title;
    private String comment;
    private int score;
    private int eventId;

}
