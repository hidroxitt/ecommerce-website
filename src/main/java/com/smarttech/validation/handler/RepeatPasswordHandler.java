package com.smarttech.validation.handler;

import com.smarttech.validation.annotation.RepeatPassword;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RepeatPasswordHandler implements ConstraintValidator<RepeatPassword, Object> {

    private String[] fields;

    @Override
    public void initialize(RepeatPassword repeatPassword) {
        this.fields = repeatPassword.fields();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(o)) return true;
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(o);
        List<Object> properties = Arrays.stream(fields)
                .map(beanWrapper::getPropertyValue)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(properties)) return true;
        Object o1 = properties.get(0);
        return properties.stream().allMatch(o1::equals);
    }
}
