package com.asellion.assignment.assignment.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="Products")
public class ProductEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Integer currentPrice;
	private Date lastUpdate;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", name=" + name + ", currentPrice=" + currentPrice + ", lastUpdate="
				+ lastUpdate + "]";
	}
	public String getName() {
		return name;
	}
	public ProductEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Integer currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public ProductEntity(String name, Integer currentPrice, Date lastUpdate) {
		super();
		this.name = name;
		this.currentPrice = currentPrice;
		this.lastUpdate = lastUpdate;
	}

	@PreUpdate
	public void onUpdate() {
		lastUpdate=new Date();
	}
	
	@PrePersist
	public void onCreate() {
		lastUpdate=new Date();
	}
	
}
