package com.varchar6.petcast.domain.review.query.dto;

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
public class ReviewDTO {
    private int id;
    private String title;
    private String comment;
    private int score;
    private Integer eventId;
}
