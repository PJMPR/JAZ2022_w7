package org.refactor.step4.dependency.inversion.tests;

import org.example.validators.ValidationResult;
import org.refactor.step4.dependency.inversion.Validator;
import org.refactor.step4.dependency.inversion.abstraction.IRegexValidation;
import org.refactor.step4.dependency.inversion.abstraction.INotNullValidation;
import org.refactor.step4.dependency.inversion.abstraction.IRangeValidation;

import java.lang.reflect.Field;
import java.util.List;

class MockNotNull implements INotNullValidation {
    @Override
    public <TClass> void validate(TClass object, ValidationResult validationResult, Field field, List<String> errors) {}
}

class MockRange implements IRangeValidation {
    public <TClass> void validate(TClass object, ValidationResult validationResult, Field field, List<String> errors) {}

}

class MockRegex implements IRegexValidation {
    public <TClass> void validate(TClass object, ValidationResult validationResult, Field field, List<String> errors) {}

}

public class UnitTest {

    public void test_something(){
        var validators = List.of(new MockNotNull(), new MockRange(), new MockRegex());
        var validator = new Validator(validators);
        // dalszy kod ...

    }
}
