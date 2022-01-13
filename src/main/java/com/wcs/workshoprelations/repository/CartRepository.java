package com.wcs.workshoprelations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wcs.workshoprelations.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
