package com.techtest.date_price.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techtest.date_price.controller.exception.ProductPriceNotFoundException;
import com.techtest.date_price.repository.projection.ProductPriceSummary;
import com.techtest.date_price.service.ProductPriceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductPriceServiceController {

	private final ProductPriceService productPriceService;

	/**
	 * 
	 * @param brandId   id of the brand
	 * @param productId id of the product
	 * @param date      optional, date for the price. If null, current time is used
	 * @return
	 * @throws ProductPriceNotFoundException
	 */
	@GetMapping("brand/{brandId}/product/{productId}")
	public ProductPriceSummary getProductPrice(@PathVariable("brandId") Integer brandId,
			@PathVariable("productId") Integer productId,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime date)
			throws ProductPriceNotFoundException {
		return productPriceService
				.getPrice(Optional.ofNullable(date).orElseGet(LocalDateTime::now), productId, brandId)
				.orElseThrow(() -> new ProductPriceNotFoundException(productId, brandId, date));
	}
}
