package org.refactor.step4.dependency.inversion;

import org.example.annotations.Regex;
import org.refactor.step4.dependency.inversion.abstraction.IRegexValidation;

import java.lang.reflect.Field;

public class RegexValidation extends ValidationBase implements IRegexValidation {


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
