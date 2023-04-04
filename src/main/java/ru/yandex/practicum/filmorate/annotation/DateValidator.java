package ru.yandex.practicum.filmorate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<DateIsAfter, LocalDate> {

    LocalDate minDate;
    Boolean allowNullValue;

    @Override
    public void initialize(DateIsAfter constraintAnnotation) {
        String[] splitDate = constraintAnnotation.min().split("-");
        allowNullValue = Boolean.valueOf(constraintAnnotation.allowNullValue());
        minDate = LocalDate.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]));
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            if (allowNullValue) {
                return true;
            } else {
                throw new IllegalArgumentException("Null date is not allowed");
            }
        }
        return date.isAfter(minDate)
                && ((date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now())));
    }
}

