package com.cristianMasterDigital.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cristianMasterDigital.app.entity.Order;
import com.cristianMasterDigital.app.entity.OrderItem;
import com.cristianMasterDigital.app.service.UserService;

@RestController
@RequestMapping("/api/pedidos")
public class OrderController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable(value = "id") Long orderId) {
		Optional<Order> oOrder = userService.findOrderById(orderId);

		if (!oOrder.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(oOrder);
	}

	// Actualizar pedido
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> update(@RequestBody Order orderDetails, @PathVariable(value = "id") Long orderId) {
		Optional<Order> order = userService.findOrderById(orderId);

		if (!order.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		List<OrderItem> items = orderDetails.getItems();

		Double previosPrice = order.get().getSubTotal();
		Double newSubTotal = 0.00;

		for (OrderItem orderItem : items) {
			newSubTotal += orderItem.getAmount();
		}

		// Verificacion de las 5 horas para editar
		Integer hoursAgo = this.getHoursAgoOrder(order.get().getCreateAt().toString());
		if (hoursAgo > 5) {
			return ResponseEntity.badRequest().build();
		}
		// Verficiacion del total de la factura
		if (newSubTotal < previosPrice) {

			return ResponseEntity.badRequest().build();
		}

		order.get().setObservation(orderDetails.getObservation());
		order.get().setItems(orderDetails.getItems());

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveOrder(order.get()));

	}

	// Cancelar Pedido
	@PutMapping("/cancelar/{id}")
	public ResponseEntity<?> cancel(@PathVariable(value = "id") Long orderId) {
		Optional<Order> order = userService.findOrderById(orderId);
		// Verificacion de las 5 horas para editar
		Integer hoursAgo = this.getHoursAgoOrder(order.get().getCreateAt().toString());
		
		if (hoursAgo > 12) {
			order.get().setStatus("Cancelado1");			
		} else {
			order.get().setStatus("Cancelado2");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveOrder(order.get()));
	}

	// Metodo que calcula las horas de diferencia entre la la fecha del pedido y la
	// actual
	public Integer getHoursAgoOrder(String orderDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String nowDate = LocalDateTime.now().toString();

		String[] date1 = nowDate.replace("T", " ").split("\\.");
		String[] date2 = orderDate.split("\\.");

		String orderDateFinal = date2[0].toString();
		String nowDateFinal = date1[0].toString();

		LocalDateTime localDateOrder = LocalDateTime.parse(orderDateFinal, formatter);
		LocalDateTime localDateNow = LocalDateTime.parse(nowDateFinal, formatter);
		System.out.println("order " + localDateOrder + " now " + localDateNow);

		Integer hours = (int) (ChronoUnit.HOURS.between(localDateOrder, localDateNow) + 1);

		return hours;
	}

}
