/**
 * Copyright (C) 2018 user name (user@email.com) the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.feign.server.web.handlers;

import com.example.feign.client.dto.errors.ErrorCodes;
import com.example.feign.client.dto.errors.ErrorDto;
import com.example.feign.client.exception.ClientException;
import com.example.feign.client.exception.ClientException2;
import com.example.feign.client.exception.ServerException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Idan Rozenfeld
 * <p>
 * It is recommended to replace the messages with those
 * that do not reveal details about the code.
 */
@Slf4j
@RestControllerAdvice
public class GlobalErrorHandlers {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorDto handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ErrorDto.builder()
                .errorCode(ErrorCodes.NOT_FOUND.toString())
                .message(ex.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorDto handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return ErrorDto.builder()
                .errorCode(ErrorCodes.NOT_FOUND.toString())
                .message(ex.getLocalizedMessage())
                .build();
    }

//    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ErrorDto handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
//        return ErrorDto.builder()
//                .errorCode(ErrorCodes.METHOD_NOT_ALLOWED.toString())
//                .errors(Collections.singleton(HttpRequestMethodErrorDto.builder()
//                        .actualMethod(ex.getMethod())
//                        .supportedMethods(ex.getSupportedHttpMethods())
//                        .build()))
//                .message(ex.getLocalizedMessage())
//                .build();
//    }
//
//    @ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ErrorDto handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
//        return ErrorDto.builder()
//                .errorCode(ErrorCodes.HTTP_MEDIA_TYPE_NOT_SUPPORTED.toString())
//                .errors(Collections.singleton(HttpMediaTypeErrorDto.builder()
//                        .mediaType(ex.getContentType())
//                        .build()))
//                .message(ex.getLocalizedMessage())
//                .build();
//    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDto handleGlobalError(Exception ex) {
        log.error("Global error handler exception: ", ex);
        return ErrorDto.builder()
                .errorCode(ErrorCodes.UNKNOWN.toString())
                .message(ex.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(feign.FeignException.class)
    public ErrorDto handleFeignError(feign.FeignException ex) {
        log.error("Global error handler exception: ", ex.getMessage());

        return ErrorDto.builder()
                .errorCode(ErrorCodes.UNKNOWN.toString())
                .message(ex.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(ClientException.class)
    public ErrorDto handleFeignError(ClientException ex) {
        log.error("ClientException1 error handler exception: ", ex.getMessage());

        return ex.getErrorDto();
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(ClientException2.class)
    public ErrorDto handleFeignError(ClientException2 ex) {
        log.error("ClientException2 error handler exception: ", ex.getMessage());

        return ex.getErrorDto();
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(ServerException.class)
    public ErrorDto handleFeignError(ServerException ex) {
        log.error("ServerException error handler exception: ", ex.getMessage());

        return ex.getErrorDto();
    }

    @ExceptionHandler(HystrixRuntimeException.class)
    @ResponseBody
    public ResponseEntity<String> handleControllerException(/*HttpServletRequest req, */HystrixRuntimeException ex) {
            HttpStatusCodeException exc = (HttpStatusCodeException)ex.getCause();
            return new ResponseEntity<>(exc.getResponseBodyAsString(), exc.getStatusCode());
//        }
//        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}