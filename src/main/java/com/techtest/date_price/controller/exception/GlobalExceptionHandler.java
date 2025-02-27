package com.techtest.date_price.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<String> handleProductNotFoundException(ProductPriceNotFoundException e) {
		log.error("Price not found for brand={}, product={}, date={}", e.getBrandId(), e.getProductId(), e.getDate(), e);
		return new ResponseEntity<String>("Price not found", HttpStatus.NOT_FOUND);
	}
}
