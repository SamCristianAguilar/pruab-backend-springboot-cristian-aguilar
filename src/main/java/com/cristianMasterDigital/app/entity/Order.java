package com.cristianMasterDigital.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String observation;
		
	private String status;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@JsonIgnoreProperties(value={"orders", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<OrderItem> items;

	
	public Order() {
		this.items = new ArrayList<>();
	}

	// Generation of the order date
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	// Calcular el subtotal del pedido
	public Double getSubTotal() {
		Double subTotal = 0.00;
		for (OrderItem item : items) {
			subTotal += item.getAmount();
		}
		return subTotal;
	}
	//Calcular el iva del pedido
	public Double getIva() {		
		Double auxIva = 0.19;
		Double iva = this.getSubTotal() * auxIva;		
		return iva;
	}
	//Calcular el total del pedido
	public Double getTotal() {
		Double aux = this.getSubTotal() + this.getIva();
		Double total = 0.00;
		if(this.status.equals("cancelado1")) {
			total = aux * 0.10;
		}else if(this.status.equals("cancelado2")) {
			total = 0.00;
		}else{
			total = aux;
		}
			
		return total;
	}
	//Calcular el valor del domicilio para pedido
	
	public Double getDomicilePrice() {
		Double domicile = 0.00;
		Double subTotal = this.getSubTotal();
		if(subTotal > 70000 && subTotal < 100000) {
			domicile = 13000.00;
		}else if(subTotal> 100000) {
			domicile = 0.00;
		}
		
		return domicile;
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	private static final long serialVersionUID = 1661819203078746262L;

}
