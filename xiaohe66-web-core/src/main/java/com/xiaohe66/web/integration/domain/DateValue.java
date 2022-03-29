package com.xiaohe66.web.integration.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * @author xiaohe
 * @since 2022.03.28 18:25
 */
@Getter
@RequiredArgsConstructor
public class DateValue implements ValueObject {

    private final LocalDate value;

    @Override
    public String toString() {
        return "DateValue(" + getValue() + ")";
    }
}
