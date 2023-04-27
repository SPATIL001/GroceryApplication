package com.onlinegrocery.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.onlinegrocery.dto.OrderDto;
import com.onlinegrocery.dto.StatusDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Order;
import com.onlinegrocery.enums.Status;
import com.onlinegrocery.exceptions.AddressNotFoundException;
import com.onlinegrocery.exceptions.OrderNotFoundException;
import com.onlinegrocery.service.AddressService;
import com.onlinegrocery.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private OrderService orderService;

	@Mock
	private AddressService addressService;

	@InjectMocks
	private OrderController orderController;
	
	@Before
	public void init() {
	MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetOrderByUserId() throws AddressNotFoundException {
	AppUser user = new AppUser();
	List<Order> orders = new ArrayList<>();
	when(orderService.getOrderByUserId(user)).thenReturn(orders);
	ResponseEntity<List<Order>> response = orderController.getOrderByUserId(user);
	assertEquals(HttpStatus.OK, response.getStatusCode());
	assertEquals(orders, response.getBody());
	}
	
	@Test
	public void testUpdateOrderStatus() throws OrderNotFoundException {
	long orderId = 1L;
	StatusDto statusDto = new StatusDto(Status.SHIPPED);
	OrderDto updatedOrderDto = new OrderDto();
	when(orderService.updateStatus(orderId, statusDto.getStatus())).thenReturn(updatedOrderDto);
	ResponseEntity<OrderDto> response = orderController.updateOrderStatus(orderId, statusDto);
	assertEquals(HttpStatus.OK, response.getStatusCode());
	assertEquals(updatedOrderDto, response.getBody());
	}
	
	@Test
	public void testCancelOrder() throws OrderNotFoundException {
	long orderId = 1L;
	when(orderService.cancelOrder(orderId)).thenReturn("Order cancelled successfully");
	String response = orderController.cancelOrder(orderId);
	assertEquals("Order cancelled successfully", response);
	}
	
	@Test
	public void testViewOrder() {
	List<Order> orders = new ArrayList<>();
	when(orderService.viewOrder()).thenReturn(orders);
	List<Order> response = orderController.viewOrder();
	assertEquals(orders, response);
	}
	
	@Test
	public void testCreateOrder() {
	OrderDto orderDto = new OrderDto();
	when(orderService.createOrder(orderDto)).thenReturn("Order created successfully");
	String response = orderController.createOrder(orderDto);
	assertEquals("Order created successfully", response);
	}
	
	@Test
	public void testViewOrderByStatus() throws OrderNotFoundException {

	int status = 1;
	Status s = Status.SHIPPED;
	List<Order> orders = new ArrayList<>();
	when(orderService.vieworderbyStatus(s)).thenReturn(orders);
	List<Order> response = orderController.vieworderbyStatus(status);
	assertEquals(orders, response);
	verify(orderService, times(1)).vieworderbyStatus(s);
	}
}
