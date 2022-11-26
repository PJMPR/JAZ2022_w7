package org.refactor.step2.open.closed;



import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Validator {


    List<ValidationBase> validators = List.of(new NotNullValidation(), new RegexValidation(), new RangeValidation());



    public <TClass> ValidationResult validate(TClass object) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setValid(true);
        validationResult.setValidatedObject(object);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            List<String> errors = new ArrayList<>();

            for (var validator : validators){

                // not null validation
                if(validator instanceof NotNullValidation)
                    ((NotNullValidation)validator).notNullValidation(object, validationResult, field, errors);

                //regex validation
                if(validator instanceof RegexValidation)
                    ((RegexValidation)validator).regexValidation(object, validationResult, field, errors);

                // range validation
                if(validator instanceof RangeValidation)
                    ((RangeValidation)validator).rangeValidation(object, validationResult, field, errors);

                //if(validator instanceof StringLengthValidation)
                    //....

            }

            if (errors.size() != 0) {
                validationResult.getNotValidFields().put(field.getName(), errors);
                validationResult.setValid(false);
            }
        }
        return validationResult;
    }






}

