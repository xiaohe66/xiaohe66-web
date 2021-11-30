package com.xiaohe66.web.gateway.http;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @since 2021.11.12 10:30
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler({Exception.class})
    public R<Void> exceptionHandler(Exception e) {

        log.error("system error", e);

        return R.build(ErrorCodeEnum.ERROR.getCode(), ErrorCodeEnum.ERROR.getMsg());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<Void> exceptionHandler(HttpRequestMethodNotSupportedException e) {

        return R.build(ErrorCodeEnum.NOT_FOUND_URL.getCode(), ErrorCodeEnum.NOT_FOUND_URL.getMsg());
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public R<Void> bindExceptionHandler(BindException e) {

        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();

        String msg = fieldErrorList.stream()
                .map(fieldError -> fieldError.getField() + fieldError.getDefaultMessage())
                .collect(Collectors.joining(","));

        return R.build(ErrorCodeEnum.PARAM_ERROR.getCode(), msg);
    }

    @ExceptionHandler(BusinessException.class)
    public R<Void> businessExceptionHandler(BusinessException e) {

        return R.build(e.getCode(), e.getMsg());
    }

}
