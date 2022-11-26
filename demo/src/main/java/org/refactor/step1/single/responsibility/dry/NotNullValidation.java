package org.refactor.step1.single.responsibility.dry;

import org.example.annotations.NotNull;
import org.example.validators.ValidationResult;
import org.refactor.step2.open.closed.liskov.ValidationBase;

import java.lang.reflect.Field;
import java.util.List;

public class NotNullValidation extends ValidationBase {

    public <TClass> void notNullValidation(TClass object, ValidationResult validationResult, Field field, List<String> errors) {
        super.validate(object, validationResult, field, errors);
    }

    protected String getMessage(Field field) {
        return field.getAnnotation(NotNull.class).message();
    }

    protected <TClass> boolean checkCondition
            (TClass object, Field field) throws IllegalAccessException {
        return field.get(object) == null;
    }

    protected boolean isAnnotationPresent(Field field) {
        return field.isAnnotationPresent(NotNull.class);
    }
}
