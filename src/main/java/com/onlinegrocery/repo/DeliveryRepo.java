package com.onlinegrocery.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinegrocery.entity.Delivery;



@Repository
public interface DeliveryRepo extends JpaRepository<Delivery, Integer> {

}
