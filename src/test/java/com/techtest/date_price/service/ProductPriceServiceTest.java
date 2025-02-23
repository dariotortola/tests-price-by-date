package com.techtest.date_price.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.techtest.date_price.repository.ProductPriceRepository;
import com.techtest.date_price.repository.projection.ProductPriceSummary;

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
		@SuppressWarnings("unchecked")
		Page<ProductPriceSummary> page = Mockito.mock(Page.class);
		Mockito.when(repository.findByProductBrandDate(eq(product), eq(brand), eq(date), any(Pageable.class)))
				.thenReturn(page);
		Mockito.when(page.stream()).thenReturn(Stream.of(summary));

		service.getPrice(date, product, brand);
		Mockito.verify(repository).findByProductBrandDate(product, brand, date, PageRequest.ofSize(1));
	}
}
