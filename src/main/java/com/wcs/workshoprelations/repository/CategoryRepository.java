package com.wcs.workshoprelations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wcs.workshoprelations.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
