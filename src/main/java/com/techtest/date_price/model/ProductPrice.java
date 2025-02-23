package com.techtest.date_price.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductPrice {

	@Id
	@Column(name = "PRICE_LIST")
	private Integer id;

	@JoinColumn(name = "BRAND_ID")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Brand brand;

	@Column(name = "START_DATE")
	private LocalDateTime startDate;

	@Column(name = "END_DATE")
	private LocalDateTime endDate;

	@JoinColumn(name = "PRODUCT_ID")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Product product;
	
	/**
	 * higher value means higher priority
	 */
	@Column(name = "PRIORITY")
	private Integer priority;
	
	@Column(name = "PRICE")
	private BigDecimal price;
	
	/**
	 * ISO of currency
	 */
	@Column(name = "CURR")
	private String currency;
}
