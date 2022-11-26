package org.refactor.step1.single.responsibility.dry;

import org.example.annotations.Regex;
import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.List;

public class RegexValidation extends ValidationBase{


    public <TClass> void regexValidation(TClass object, ValidationResult validationResult, Field field, List<String> errors) {
        super.validate(object, validationResult,field, errors);
    }

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
