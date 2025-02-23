package com.techtest.date_price.controller.openapi;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "ProductPrice", description = "Product price details")
@Getter
public class ProductPriceSummarySchema {

	@Schema(description = "brand id")
	private Integer brandId;
	@Schema(description = "product id")
	private Integer productId;
	@Schema(description = "price list id")
	private Integer priceListId;
	@Schema(description = "when this price starts being valid", example = "2020-06-27T16:00:00")
	private LocalDateTime startDate;
	@Schema(description = "when this price stops being valid", example = "2020-06-27T16:00:00")
	private LocalDateTime endDate;
	@Schema(description = "price")
	private BigDecimal price;
	@Schema(description = "ISO of currency", example = "EUR")
	private String currency;
}
