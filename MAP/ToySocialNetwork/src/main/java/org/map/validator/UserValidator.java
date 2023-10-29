package org.map.validator;

import org.map.domain.User;
import org.map.exception.ValidatorException;

import java.util.function.Predicate;

public class UserValidator implements Validator<User>{
    @Override
    public void validate(User entity) {
        Predicate<String> checkEmpty = String::isEmpty;

        StringBuilder errorMessage = new StringBuilder();

        if(checkEmpty.test(entity.getFirstName()))
            errorMessage.append("First Name is null ");
        if(checkEmpty.test(entity.getLastName()))
            errorMessage.append("Last Name is null");

        if(!checkEmpty.test(errorMessage.toString()))
            throw new ValidatorException(errorMessage.toString());
    }
}
