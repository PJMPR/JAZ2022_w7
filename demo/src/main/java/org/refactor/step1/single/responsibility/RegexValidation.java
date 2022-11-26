package org.refactor.step1.single.responsibility;

import org.example.annotations.Regex;
import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.List;

public class RegexValidation {


    public <TClass> void regexValidation(TClass object, ValidationResult validationResult, Field field, List<String> errors) {
        if (field.isAnnotationPresent(Regex.class)){
            try {
                if (!field.get(object).toString().matches(field.getAnnotation(Regex.class).pattern())){

                    errors.add(field.getAnnotation(Regex.class).message());
                    validationResult.setValid(false);

                }
            } catch (IllegalAccessException exc) {
                exc.printStackTrace();
            }
        }
    }
}
