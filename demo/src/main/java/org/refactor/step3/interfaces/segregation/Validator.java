package org.refactor.step3.interfaces.segregation;



import org.example.validators.ValidationResult;
import org.refactor.step3.interfaces.segregation.abstraction.INotNullValidation;
import org.refactor.step3.interfaces.segregation.abstraction.IRangeValidation;
import org.refactor.step3.interfaces.segregation.abstraction.IRegexValidation;
import org.refactor.step3.interfaces.segregation.abstraction.IValidate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Validator {
    List<IValidate> validators = new ArrayList<>();

    public Validator() {
    }

    public Validator(List<ValidationBase> validators) {
        this.validators.addAll(validators);
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

    public void addValidation(INotNullValidation validation){ validators.add(validation); }
    public void addValidation(IRangeValidation validation){ validators.add(validation); }
    public void addValidation(IRegexValidation validation){ validators.add(validation); }

    public void addValidation(ValidationBase validation){ validators.add(validation); }






}

