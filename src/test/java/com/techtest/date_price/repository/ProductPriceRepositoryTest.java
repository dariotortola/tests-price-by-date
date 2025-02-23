package com.techtest.date_price.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import com.techtest.date_price.repository.projection.ProductPriceSummary;

@DataJpaTest(properties = "spring.sql.init.mode=always")
class ProductPriceRepositoryTest {

	@Autowired
	private ProductPriceRepository productPriceRepository;

	@Test
	void test1() {
		testPriceExpected(LocalDateTime.of(2020, 6, 14, 10, 0), BigDecimal.valueOf(35.50), 1);
	}

	@Test
	void test2() {
		testPriceExpected(LocalDateTime.of(2020, 6, 14, 16, 0), BigDecimal.valueOf(25.45), 2);
	}

	@Test
	void test3() {
		testPriceExpected(LocalDateTime.of(2020, 6, 14, 21, 0), BigDecimal.valueOf(35.50), 1);
	}

	@Test
	void test4() {
		testPriceExpected(LocalDateTime.of(2020, 6, 15, 10, 0), BigDecimal.valueOf(30.50), 3);
	}

	@Test
	void test5() {
		testPriceExpected(LocalDateTime.of(2020, 6, 16, 21, 0), BigDecimal.valueOf(38.95), 4);
	}

	void testPriceExpected(LocalDateTime date, BigDecimal expectedPrice, Integer expectedPriceListId) {
		ProductPriceSummary result = productPriceRepository
				.findByProductBrandDate(35455, 1, date, PageRequest.ofSize(1)).stream()
				.findFirst().orElseThrow();
		Assertions.assertEquals(0, expectedPrice.compareTo(result.getPrice()));
		Assertions.assertEquals(expectedPriceListId, result.getPriceListId());
	}

}
