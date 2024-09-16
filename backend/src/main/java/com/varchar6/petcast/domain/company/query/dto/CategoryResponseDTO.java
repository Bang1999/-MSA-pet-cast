package com.varchar6.petcast.domain.company.query.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryResponseDTO {
    private String companyId;
    private List<String> name;
}
