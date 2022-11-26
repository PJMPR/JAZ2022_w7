package org.refactor.step3.interfaces.segregation.unit.tests;

import org.example.validators.ValidationResult;
import org.refactor.step3.interfaces.segregation.NotNullValidation;
import org.refactor.step3.interfaces.segregation.RangeValidation;
import org.refactor.step3.interfaces.segregation.RegexValidation;
import org.refactor.step3.interfaces.segregation.Validator;
import org.refactor.step3.interfaces.segregation.abstraction.INotNullValidation;
import org.refactor.step3.interfaces.segregation.abstraction.IRangeValidation;
import org.refactor.step3.interfaces.segregation.abstraction.IRegexValidation;

import java.lang.reflect.Field;
import java.util.List;

class MockNotNull implements INotNullValidation{
    @Override
    public <TClass> void validate(TClass object, ValidationResult validationResult, Field field, List<String> errors) {}
}

class MockRange implements IRangeValidation{
    public <TClass> void validate(TClass object, ValidationResult validationResult, Field field, List<String> errors) {}

}

class MockRegex implements IRegexValidation {
    public <TClass> void validate(TClass object, ValidationResult validationResult, Field field, List<String> errors) {}

}

public class UnitTest {

    public void test_something(){
        new Validator().addValidation(new MockNotNull());
        new Validator().addValidation(new MockRange());
        new Validator().addValidation(new MockRegex());

        /**
         *  ;-[
         */
        new Validator(List.of(new NotNullValidation(), new RangeValidation(), new RegexValidation()));


        //dalsza część kodu...
    }
}
