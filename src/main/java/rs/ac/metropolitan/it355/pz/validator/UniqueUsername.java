package rs.ac.metropolitan.it355.pz.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueUsernameValidator.class)
@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface UniqueUsername {

    public String message() default "That username is already taken. Please pick another one.";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}