package com.romaincaron.journalize.repository;

import com.romaincaron.journalize.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
