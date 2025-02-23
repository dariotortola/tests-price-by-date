package com.techtest.date_price.controller.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductPriceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3946690687848159714L;
	private final Integer productId;
	private final Integer brandId;
	private final LocalDateTime date;
}
