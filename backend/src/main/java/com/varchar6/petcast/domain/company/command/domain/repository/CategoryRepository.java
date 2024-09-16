package com.varchar6.petcast.domain.company.command.domain.repository;

import com.varchar6.petcast.domain.company.command.domain.aggregate.Category;
import com.varchar6.petcast.domain.company.command.domain.aggregate.CategoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, CategoryPK> {
    void deleteByCompanyId(int companyId);
}
