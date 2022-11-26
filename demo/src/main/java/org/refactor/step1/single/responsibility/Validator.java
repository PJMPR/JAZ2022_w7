package org.refactor.step1.single.responsibility;



import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Validator {

    public <TClass> ValidationResult validate(TClass object) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setValid(true);
        validationResult.setValidatedObject(object);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            List<String> errors = new ArrayList<>();

            // not null validation
            new NotNullValidation().notNullValidation(object, validationResult, field, errors);


            //regex validation
            new RegexValidation().regexValidation(object, validationResult, field, errors);

            // range validation
            new RangeValidation().rangeValidation(object, validationResult, field, errors);

            if (errors.size() != 0) {
                validationResult.getNotValidFields().put(field.getName(), errors);
                validationResult.setValid(false);
            }
        }
        return validationResult;
    }






}

