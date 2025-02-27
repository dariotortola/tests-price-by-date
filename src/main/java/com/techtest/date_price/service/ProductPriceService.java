package com.techtest.date_price.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techtest.date_price.repository.ProductPriceRepository;
import com.techtest.date_price.repository.projection.ProductPriceSummary;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductPriceService implements IProductPriceService {

	private final ProductPriceRepository repository;

	/**
	 * A product of a brand may have different prices at different times, hence we
	 * need to specify the date for the price
	 * 
	 * @param date      date the price is valid
	 * @param productId id of the product
	 * @param brandId   id of the brand
	 * @return price valid for the product, brand and date, if defined
	 */
	public Optional<ProductPriceSummary> getPrice(LocalDateTime date, Integer productId, Integer brandId) {
		return repository.findFirstByProductBrandDate(productId, brandId, date);
	}
}
