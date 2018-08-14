package com.example.feign.client.exception;

import com.example.feign.client.dto.errors.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientException2 extends RuntimeException {
    private ErrorDto errorDto;
}
