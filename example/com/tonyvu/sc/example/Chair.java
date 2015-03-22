package com.tonyvu.sc.example;

import java.math.BigDecimal;

import com.tonyvu.sc.model.Saleable;

public class Chair implements Saleable {
	
	private String name;
	private BigDecimal price;
	private String manufacturer;

	public Chair() {
		super();
	}
	
	public Chair(String name, String manufacturer, BigDecimal price) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.price = price;
	}
	
	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
