package org.refactor.step4.dependency.inversion;

import org.example.annotations.NotNull;
import org.refactor.step4.dependency.inversion.abstraction.INotNullValidation;

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
