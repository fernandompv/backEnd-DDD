package com.demo.inditex.dtos;

import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

public record ErrorDTO(HttpStatus status, String message, OffsetDateTime date) {
}
