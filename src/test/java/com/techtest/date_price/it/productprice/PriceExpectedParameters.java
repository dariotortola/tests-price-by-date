package com.techtest.date_price.it.productprice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PriceExpectedParameters {

	private final LocalDateTime date;
	private final BigDecimal expectedPrice;
	private final Integer expectedPriceListId;
}
