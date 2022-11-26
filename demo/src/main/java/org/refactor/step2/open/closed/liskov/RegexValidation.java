package org.refactor.step2.open.closed.liskov;

import org.example.annotations.Regex;

import java.lang.reflect.Field;

public class RegexValidation extends ValidationBase {


    @Override
    protected String getMessage(Field field) {
        return field.getAnnotation(Regex.class).message();
    }

    @Override
    protected <TClass> boolean checkCondition(TClass object, Field field) throws IllegalAccessException {
        return !field.get(object).toString().matches(field.getAnnotation(Regex.class).pattern());
    }

    @Override
    protected boolean isAnnotationPresent(Field field) {
        return field.isAnnotationPresent(Regex.class);
    }
}
