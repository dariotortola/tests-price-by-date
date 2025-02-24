package com.techtest.date_price.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techtest.date_price.model.ProductPrice;
import com.techtest.date_price.repository.projection.ProductPriceSummary;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {

	@Query("SELECT p FROM ProductPrice p WHERE p.product.id = :productId AND p.brand.id = :brandId AND p.startDate <= :date AND p.endDate >= :date ORDER BY p.priority DESC LIMIT 1")
	Optional<ProductPriceSummary> findFirstByProductBrandDate(
			@Param("productId") Integer productId, 
			@Param("brandId") Integer brandId, 
			@Param("date") LocalDateTime date);
}
