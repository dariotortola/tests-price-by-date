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
	void day14time10() {
		Assertions.assertTrue(productPriceRepository.count() > 0L);
		ProductPriceSummary result = productPriceRepository
				.findByProductBrandDate(35455, 1, LocalDateTime.of(2020, 6, 14, 10, 0), PageRequest.ofSize(1)).stream()
				.findFirst().orElseThrow();
		Assertions.assertEquals(BigDecimal.valueOf(435.5), result.getPrice());
	}
}
