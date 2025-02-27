package com.techtest.date_price.it.productprice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class ParameterSupplier {

	public static Stream<PriceExpectedParameters> getPriceExpectedParameters() {
		return Stream.of(
				new PriceExpectedParameters(LocalDateTime.of(2020, 6, 14, 10, 0), BigDecimal.valueOf(35.50), 1),
				new PriceExpectedParameters(LocalDateTime.of(2020, 6, 14, 16, 0), BigDecimal.valueOf(25.45), 2),
				new PriceExpectedParameters(LocalDateTime.of(2020, 6, 14, 21, 0), BigDecimal.valueOf(35.50), 1),
				new PriceExpectedParameters(LocalDateTime.of(2020, 6, 15, 10, 0), BigDecimal.valueOf(30.50), 3),
				new PriceExpectedParameters(LocalDateTime.of(2020, 6, 16, 21, 0), BigDecimal.valueOf(38.95), 4));
	}
}
