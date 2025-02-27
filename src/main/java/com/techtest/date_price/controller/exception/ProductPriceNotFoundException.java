package com.techtest.date_price.controller.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductPriceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3946690687848159714L;
	private final Integer productId;
	private final Integer brandId;
	private final LocalDateTime date;
}
