package org.map.validator;

import org.map.exception.ValidatorException;

public interface Validator<T> {
        void validate(T entity) throws ValidatorException;
}
