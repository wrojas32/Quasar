package com.mercadolibre.quasar.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@JsonInclude(value = Include.NON_NULL)
public class ResponseError {
    private String message;
    private Integer statusCode;
    private String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN));

    private static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public ResponseError(String message) {
        this(message, null);
    }

    public ResponseError(String message, Integer statusCode) {
        super();
        this.message = message;
        this.statusCode = statusCode;
    }
}