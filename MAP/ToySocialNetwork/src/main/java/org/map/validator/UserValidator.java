package org.map.validator;

import org.map.domain.User;
import org.map.exception.ValidatorException;

public class UserValidator implements Validator<User>{
    @Override
    public void validate(User entity) {

        StringBuilder errorMessage = new StringBuilder();
        if(entity.getFirstName().isEmpty())
            errorMessage.append("First Name is null ");
        if(entity.getLastName().isEmpty())
            errorMessage.append("Last Name is null");

        if(!errorMessage.isEmpty())
            throw new ValidatorException(errorMessage.toString());
    }
}
