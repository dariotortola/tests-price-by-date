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
import com.techtest.date_price.controller.openapi.ProductPriceSummarySchema;
import com.techtest.date_price.repository.projection.ProductPriceSummary;
import com.techtest.date_price.service.ProductPriceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductPriceController {

	private final ProductPriceService productPriceService;

	/**
	 * Finds information about the price that should be applied to a product
	 * 
	 * @param brandId   id of the brand
	 * @param productId id of the product
	 * @param date      optional, date for the price. If null, current time is used
	 * @return
	 * @throws ProductPriceNotFoundException
	 */
	@Operation(summary = "Get price to apply to a product.", parameters = {
			@Parameter(name = "brandId", description = "Id of the brand"),
			@Parameter(name = "productId", description = "Id of the product"),
			@Parameter(name = "date", description = "date in ISO Date Time Format (yyyy-MM-dd'T'HH:mm:ss.SSS). Uses current date and time if null.", example = "2000-10-31T01:30:00") })
	@ApiResponse(responseCode = "200", description = "Price found", content = @Content(
			mediaType = "application/json", schema = @Schema(
					implementation = ProductPriceSummarySchema.class)))
	@ApiResponse(responseCode = "404", description = "Price not found for the combination of product, brand and date", content = @Content)
	@GetMapping("brand/{brandId}/product/{productId}/price")
	public ProductPriceSummary getProductPrice(@PathVariable("brandId") Integer brandId,
			@PathVariable("productId") Integer productId,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime date)
			throws ProductPriceNotFoundException {
		return productPriceService.getPrice(Optional.ofNullable(date).orElseGet(LocalDateTime::now), productId, brandId)
				.orElseThrow(() -> new ProductPriceNotFoundException(productId, brandId, date));
	}
}
