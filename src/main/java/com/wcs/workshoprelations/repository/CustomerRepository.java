package com.wcs.workshoprelations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wcs.workshoprelations.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
