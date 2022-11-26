package org.refactor.step3.interfaces.segregation;

import org.example.validators.ValidationResult;
import org.refactor.step3.interfaces.segregation.abstraction.IValidate;

import java.lang.reflect.Field;
import java.util.List;

public abstract class ValidationBase implements IValidate {

    @Override
    public  <TClass> void validate(TClass object, ValidationResult validationResult, Field field, List<String> errors) {
        if (isAnnotationPresent(field)) {
            try {
                if (checkCondition(object, field)) {
                    errors.add(getMessage(field));;
                    validationResult.setValid(false);

                }
            } catch (IllegalAccessException exc) {
                exc.printStackTrace();
            }
        }
    }

    protected abstract String getMessage(Field field);

    protected abstract <TClass> boolean checkCondition(TClass object, Field field) throws IllegalAccessException;

    protected abstract boolean isAnnotationPresent(Field field);

}
