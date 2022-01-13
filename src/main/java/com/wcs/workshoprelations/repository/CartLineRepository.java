package com.wcs.workshoprelations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wcs.workshoprelations.entity.CartLine;

@Repository
public interface CartLineRepository extends JpaRepository<CartLine, Long> {

}
