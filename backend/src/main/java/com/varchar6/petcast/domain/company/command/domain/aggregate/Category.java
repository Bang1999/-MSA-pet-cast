package com.varchar6.petcast.domain.company.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tbl_company_catgory")
@IdClass(CategoryPK.class)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "category_id")
    private int categoryId;

    @Id
    @Column(name = "company_id")
    private int companyId;
}
