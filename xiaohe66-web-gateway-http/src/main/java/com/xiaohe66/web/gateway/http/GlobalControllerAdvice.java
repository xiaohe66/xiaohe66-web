package com.xiaohe66.web.gateway.http;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
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
public class GlobalControllerAdvice {

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
