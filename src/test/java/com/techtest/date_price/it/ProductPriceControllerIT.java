package com.techtest.date_price.it;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import com.techtest.date_price.it.productprice.PriceExpectedParameters;
import com.techtest.date_price.it.productprice.ProductPriceSummaryDto;
import com.techtest.date_price.repository.projection.ProductPriceSummary;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class ProductPriceControllerIT {

	@Autowired
	private TestRestTemplate restTemplate;

	@ParameterizedTest
	@MethodSource("com.techtest.date_price.it.productprice.ParameterSupplier#getPriceExpectedParameters")
	void testPriceExpected(PriceExpectedParameters params) {
		String url = UriComponentsBuilder.fromPath("/brand/1/product/35455/price").queryParam("date", params.getDate())
				.toUriString();
		HttpEntity<? extends ProductPriceSummary> result = restTemplate.getForEntity(url, ProductPriceSummaryDto.class);
		Assertions.assertEquals(0, params.getExpectedPrice().compareTo(result.getBody().getPrice()));
		Assertions.assertEquals(params.getExpectedPriceListId(), result.getBody().getPriceListId());
	}
	
	@Test
	void whenNotFound_then404() {
		ResponseEntity<String> result = restTemplate.getForEntity("/brand/2/product/35455/price", String.class);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}
}
