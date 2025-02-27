package com.techtest.date_price.it;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.techtest.date_price.it.productprice.PriceExpectedParameters;
import com.techtest.date_price.repository.ProductPriceRepository;
import com.techtest.date_price.repository.projection.ProductPriceSummary;

@DataJpaTest(properties = "spring.datasource.initialization-mode=never")
@Sql(scripts = "/test-data.sql")
class ProductPriceRepositoryIT {

	@Autowired
	private ProductPriceRepository productPriceRepository;

	@ParameterizedTest
	@MethodSource("com.techtest.date_price.it.productprice.ParameterSupplier#getPriceExpectedParameters")
	void testPriceExpected(PriceExpectedParameters params) {
		ProductPriceSummary result = productPriceRepository.findFirstByProductBrandDate(35455, 1, params.getDate()).orElseThrow();
		Assertions.assertEquals(0, params.getExpectedPrice().compareTo(result.getPrice()));
		Assertions.assertEquals(params.getExpectedPriceListId(), result.getPriceListId());
	}
}
