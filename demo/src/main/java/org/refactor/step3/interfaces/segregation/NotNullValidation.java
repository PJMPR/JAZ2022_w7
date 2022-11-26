package org.refactor.step3.interfaces.segregation;

import org.example.annotations.NotNull;
import org.refactor.step3.interfaces.segregation.abstraction.INotNullValidation;

import java.lang.reflect.Field;

public class NotNullValidation extends ValidationBase implements INotNullValidation {

    protected  String getMessage(Field field) {
        return field.getAnnotation(NotNull.class).message();
    }

    protected <TClass> boolean checkCondition
            (TClass object, Field field) throws IllegalAccessException {
        return field.get(object) == null;
    }

    protected boolean isAnnotationPresent(Field field) {
        return field.isAnnotationPresent(NotNull.class);
    }
}
