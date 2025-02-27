package com.techtest.date_price.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.techtest.date_price.repository.projection.ProductPriceSummary;

public interface IProductPriceService {

	Optional<ProductPriceSummary> getPrice(LocalDateTime date, Integer productId, Integer brandId);
}
