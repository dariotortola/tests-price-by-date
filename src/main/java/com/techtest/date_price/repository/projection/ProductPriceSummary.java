package com.techtest.date_price.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

public interface ProductPriceSummary {

	@Value("#{target.product.id}")
	Integer getProductId();

	@Value("#{target.brand.id}")
	Integer getBrandId();

	@Value("#{target.id}")
	Integer getPriceListId();

	LocalDateTime getStartDate();

	LocalDateTime getEndDate();

	BigDecimal getPrice();

	String getCurrency();
}
