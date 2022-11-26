package org.refactor.step1.single.responsibility.dry;



import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Validator {

    NotNullValidation notNUll = new NotNullValidation();
    RegexValidation regex = new RegexValidation();
    RangeValidation range = new RangeValidation();

    public <TClass> ValidationResult validate(TClass object) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setValid(true);
        validationResult.setValidatedObject(object);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            List<String> errors = new ArrayList<>();

            // not null validation
            notNUll.notNullValidation(object, validationResult, field, errors);

            //regex validation
            regex.regexValidation(object, validationResult, field, errors);

            // range validation
            range.rangeValidation(object, validationResult, field, errors);

            if (errors.size() != 0) {
                validationResult.getNotValidFields().put(field.getName(), errors);
                validationResult.setValid(false);
            }
        }
        return validationResult;
    }






}

