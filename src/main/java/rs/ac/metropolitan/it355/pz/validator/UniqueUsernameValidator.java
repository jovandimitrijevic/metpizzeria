package rs.ac.metropolitan.it355.pz.validator;


import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.metropolitan.it355.pz.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private UserService userService;

    @Autowired
    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    public UniqueUsernameValidator() {
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.isUsernameAlreadyInUse(username);
    }
}
