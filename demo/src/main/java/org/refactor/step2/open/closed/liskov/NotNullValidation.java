package org.refactor.step2.open.closed.liskov;

import org.example.annotations.NotNull;

import java.lang.reflect.Field;

public class NotNullValidation extends ValidationBase {

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
