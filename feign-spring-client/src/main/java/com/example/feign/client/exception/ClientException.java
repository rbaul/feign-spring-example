package com.example.feign.client.exception;

import com.example.feign.client.dto.errors.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientException extends RuntimeException {
    private ErrorDto errorDto;
}
