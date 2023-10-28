package org.map.validator;

import org.map.domain.Friendship;
import org.map.domain.User;
import org.map.exception.ValidatorException;

import java.util.Objects;

public class FriendshipValidator implements Validator<Friendship> {

    private final UserValidator userValidator = new UserValidator();
    @Override
    public void validate(Friendship entity) throws ValidatorException {


        User firstUser = entity.getFirstUser();
        User secondUser = entity.getSecondUser();
        userValidator.validate(firstUser);
        userValidator.validate(secondUser);
        if(Objects.equals(firstUser.getID(), secondUser.getID()))
            throw new ValidatorException("User ID are the same!");
    }
}
