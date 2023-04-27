package com.onlinegrocery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.onlinegrocery.dto.DeliveryDto;
import com.onlinegrocery.entity.Delivery;
import com.onlinegrocery.repo.DeliveryRepo;
import com.onlinegrocery.serviceImpl.DeliveryServiceImpl;

public class DeliveryServiceTest {

	@Mock
	private DeliveryRepo deliveryRepo;
	
	@InjectMocks
	private DeliveryServiceImpl deliveryService;
	
	@Before
	public void init() {
	MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddDeliverySlots() {
	DeliveryDto deliveryDto = new DeliveryDto();
	Delivery delivery = new Delivery();
	delivery.setDeliveryId(deliveryDto.getDeliveryId());
	delivery.setDeliveryDate(deliveryDto.getDeliveryDate());
	delivery.setDeliveryTime(deliveryDto.getDeliveryTime());
	when(deliveryRepo.save(delivery)).thenReturn(delivery);
	
	DeliveryDto result = deliveryService.addDeliverySlots(deliveryDto);
	assertEquals(deliveryDto.getDeliveryId(), result.getDeliveryId());
	assertEquals(deliveryDto.getDeliveryDate(), result.getDeliveryDate());
	assertEquals(deliveryDto.getDeliveryTime(), result.getDeliveryTime());
	when(deliveryRepo.save(Mockito.any(Delivery.class))).thenReturn(new Delivery());
	}
}
