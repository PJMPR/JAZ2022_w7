package org.refactor.step2.open.closed.liskov;



import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Validator {
    List<ValidationBase> validators = new ArrayList<>();

    public Validator(List<ValidationBase> validators) {
        this.validators = validators;
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

    public void addValidation(NotNullValidation validation){ validators.add(validation); }
    public void addValidation(RangeValidation validation){ validators.add(validation); }
    public void addValidation(RegexValidation validation){ validators.add(validation); }

    public void addValidation(ValidationBase validation){ validators.add(validation); }






}

