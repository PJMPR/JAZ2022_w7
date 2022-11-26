package org.refactor.step2.open.closed.liskov;

import org.example.annotations.Range;

import java.lang.reflect.Field;

public class RangeValidation extends ValidationBase {

    @Override
    protected String getMessage(Field field) {
        return field.getAnnotation(Range.class).message();
    }

    @Override
    protected <TClass> boolean checkCondition(TClass object, Field field) throws IllegalAccessException {
        return !((int) field.get(object) > (int) field.getAnnotation(Range.class).min()
                && (int) field.get(object) < (int) field.getAnnotation(Range.class).max());
    }

    @Override
    protected boolean isAnnotationPresent(Field field) {
        return field.isAnnotationPresent(Range.class);
    }
}
