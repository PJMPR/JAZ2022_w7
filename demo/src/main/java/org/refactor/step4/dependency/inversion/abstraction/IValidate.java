package org.refactor.step4.dependency.inversion.abstraction;

import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.List;

public interface IValidate {
    <TClass> void validate(TClass object, ValidationResult validationResult, Field field, List<String> errors);
}
