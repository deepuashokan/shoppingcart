package com.tonyvu.sc.example;

import java.math.BigDecimal;

import com.tonyvu.sc.model.Saleable;

public class BoardGame implements Saleable {
	
	private String name;
	private String author;
	private BigDecimal price;
	private String description;
	
	public BoardGame() {
		super();
	}
	
	public BoardGame(String name, String author, String description, BigDecimal price) {
		this.name = name;
		this.author = author;
		this.description = description;
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
	
	public String getDescription() {
		return description;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
}
