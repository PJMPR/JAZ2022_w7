package org.refactor.step1.single.responsibility;

import org.example.annotations.NotNull;
import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.List;

public class NotNullValidation {

    public <TClass> void notNullValidation(TClass object, ValidationResult validationResult, Field field, List<String> errors) {
        if (field.isAnnotationPresent(NotNull.class)) {
            try {
                if (field.get(object) == null) {
                    errors.add(field.getAnnotation(NotNull.class).message());;
                    validationResult.setValid(false);

                }
            } catch (IllegalAccessException exc) {
                exc.printStackTrace();
            }
        }
    }
}
