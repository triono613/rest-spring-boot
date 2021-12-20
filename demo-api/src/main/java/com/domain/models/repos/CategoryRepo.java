package com.domain.models.repos;

import com.domain.models.entities.Category;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepo extends PagingAndSortingRepository<Category,Long> {
    
}
