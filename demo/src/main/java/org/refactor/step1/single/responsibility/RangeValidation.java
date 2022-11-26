package org.refactor.step1.single.responsibility;

import org.example.annotations.Range;
import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.List;

public class RangeValidation {

    public <TClass> void rangeValidation(TClass object, ValidationResult validationResult, Field field, List<String> errors) {
        if (field.isAnnotationPresent(Range.class)) {
            try {

                if (!((int) field.get(object) > (int) field.getAnnotation(Range.class).min()
                        && (int) field.get(object) < (int) field.getAnnotation(Range.class).max())) {

                    errors.add(field.getAnnotation(Range.class).message());
                    validationResult.setValid(false);

                }
            } catch (IllegalAccessException exc) {
                exc.printStackTrace();
            }
        }
    }
}
