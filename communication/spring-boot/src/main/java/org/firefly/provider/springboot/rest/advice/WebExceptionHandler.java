package org.firefly.provider.springboot.rest.advice;

import org.firefly.provider.springboot.domain.exception.ParameterBillException;
import org.firefly.provider.springboot.domain.exception.UnknownBillException;
import org.firefly.provider.springboot.rest.response.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    @ExceptionHandler
    public Response<String> parameterException(ParameterBillException e) {
        return new Response<>(400, e.getMessage(), null);
    }

    @ExceptionHandler
    public Response<String> unknownException(UnknownBillException e) {
        return new Response<>(505, e.getMessage(), null);
    }

    @ExceptionHandler
    public Response<String> generalException(Exception e) {
        return new Response<>(501, e.getMessage(), null);
    }
}