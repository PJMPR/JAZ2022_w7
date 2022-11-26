package org.refactor.step2.open.closed;

import org.example.annotations.Range;
import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.List;

public class RangeValidation extends ValidationBase {

    public <TClass> void rangeValidation(TClass object, ValidationResult validationResult, Field field, List<String> errors) {
        super.validate(object, validationResult, field, errors);
    }

    @Override
    protected String getMessage(Field field) {
        return field.getAnnotation(Range.class).message();
    }

    @Override
    protected <TClass> boolean checkCondition(TClass object, Field field) throws IllegalAccessException {
        return !((int) field.get(object) > (int) field.getAnnotation(Range.class).min()
                && (int) field.get(object) < (int) field.getAnnotation(Range.class).max());
    }

    @Override
    protected boolean isAnnotationPresent(Field field) {
        return field.isAnnotationPresent(Range.class);
    }
}
