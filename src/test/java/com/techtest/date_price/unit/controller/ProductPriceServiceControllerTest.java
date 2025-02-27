package com.techtest.date_price.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techtest.date_price.controller.ProductPriceController;
import com.techtest.date_price.controller.exception.ProductPriceNotFoundException;
import com.techtest.date_price.repository.projection.ProductPriceSummary;
import com.techtest.date_price.service.ProductPriceService;

@ExtendWith(MockitoExtension.class)
public class ProductPriceServiceControllerTest {

	@InjectMocks
	private ProductPriceController controller;

	@Mock
	private ProductPriceService service;

	@Test
	void whenDatePassDate() throws ProductPriceNotFoundException {
		LocalDateTime date = LocalDateTime.now();
		Integer brand = 1;
		Integer product = 2;

		ProductPriceSummary result = Mockito.mock(ProductPriceSummary.class);
		Mockito.when(service.getPrice(date, product, brand)).thenReturn(Optional.of(result));

		ProductPriceSummary answer = controller.getProductPrice(brand, product, date);

		Assertions.assertEquals(result, answer);
	}

	@Test
	void whenNoDatePassCurrentDate() throws ProductPriceNotFoundException {

		Integer brand = 1;
		Integer product = 2;

		ProductPriceSummary result = Mockito.mock(ProductPriceSummary.class);
		Mockito.when(service.getPrice(any(LocalDateTime.class), eq(product), eq(brand)))
				.thenReturn(Optional.of(result));

		ProductPriceSummary answer = controller.getProductPrice(brand, product, null);

		Assertions.assertEquals(result, answer);
	}

	@Test
	void whenNoPriceThrow() throws ProductPriceNotFoundException {

		Integer brand = 1;
		Integer product = 2;

		Assertions.assertThrows(ProductPriceNotFoundException.class,
				() -> controller.getProductPrice(brand, product, null));
	}
}
