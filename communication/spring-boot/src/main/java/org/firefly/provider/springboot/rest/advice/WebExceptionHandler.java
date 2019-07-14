package org.firefly.provider.springboot.rest.advice;

import org.firefly.provider.springboot.domain.exception.ParameterBillException;
import org.firefly.provider.springboot.domain.exception.InternalBillException;
import org.firefly.provider.springboot.rest.response.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 统一异常处理，使用了Spring AOP特性：
// http://www.zhaojun.im/springboot-exception/
// http://www.zhaojun.im/springboot-exception-expand/
// 前端只需要定义success事件处理就可以了，不需要定义error事件。
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    @ExceptionHandler
    public Response<String> parameterException(ParameterBillException e) {
        return new Response<>(400, e.getMessage(), null);
    }

    @ExceptionHandler
    public Response<String> internalException(InternalBillException e) {
        return new Response<>(501, e.getMessage(), null);
    }

    @ExceptionHandler
    public Response<String> unknownException(Exception e) {
        return new Response<>(504, e.getMessage(), null);
    }
}