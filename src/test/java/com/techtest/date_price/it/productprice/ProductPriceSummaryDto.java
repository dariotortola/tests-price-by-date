package com.techtest.date_price.it.productprice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.techtest.date_price.repository.projection.ProductPriceSummary;

import lombok.Getter;
import lombok.Setter;

/**
 * TestRestTemplate requires a concrete class, this is cleaner than using a map
 */
@Getter
@Setter
public class ProductPriceSummaryDto implements ProductPriceSummary {

	private Integer productId;
	private Integer brandId;
	private Integer priceListId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private BigDecimal price;
	private String currency;
}
