package com.techtest.date_price.unit.service;

import static org.mockito.ArgumentMatchers.eq;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techtest.date_price.repository.ProductPriceRepository;
import com.techtest.date_price.repository.projection.ProductPriceSummary;
import com.techtest.date_price.service.ProductPriceService;

@ExtendWith(MockitoExtension.class)
public class ProductPriceServiceTest {

	@InjectMocks
	private ProductPriceService service;
	@Mock
	private ProductPriceRepository repository;

	@Test
	void whenGetPriceThenPageOfOne() {
		Integer product = 1;
		Integer brand = 2;
		LocalDateTime date = LocalDateTime.now();

		ProductPriceSummary summary = Mockito.mock(ProductPriceSummary.class);
		Mockito.when(repository.findFirstByProductBrandDate(eq(product), eq(brand), eq(date)))
				.thenReturn(Optional.of(summary));

		service.getPrice(date, product, brand);
		Mockito.verify(repository).findFirstByProductBrandDate(product, brand, date);
	}
}
