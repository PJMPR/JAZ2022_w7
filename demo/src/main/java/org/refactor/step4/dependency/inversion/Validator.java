package org.refactor.step4.dependency.inversion;



import org.example.validators.ValidationResult;
import org.refactor.step4.dependency.inversion.abstraction.IValidate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Validator {
    private final List<IValidate> validators;

    public Validator(List<IValidate> validators) {
        this.validators= validators;
    }

    public <TClass> ValidationResult validate(TClass object) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setValid(true);
        validationResult.setValidatedObject(object);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            List<String> errors = new ArrayList<>();

            for (var validator : validators){
                validator.validate(object, validationResult, field, errors);
            }

            if (errors.size() != 0) {
                validationResult.getNotValidFields().put(field.getName(), errors);
                validationResult.setValid(false);
            }
        }
        return validationResult;
    }
}

