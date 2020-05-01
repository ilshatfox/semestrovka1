package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyAnnotValidator implements ConstraintValidator<MyAnnot, Integer> {
    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
//        System.out.println("ffffffffff");
        return true;
    }
}
