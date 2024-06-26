package com.smarttech.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;
import java.util.Objects;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class ValidationException extends BusinessException {

    private Object[] params;
    private boolean is18N;

    public ValidationException(String message, Object...params) {
        super(message, null);
        this.params = params;
        this.is18N = false;
    }

    @Override
    public String getMessage() {
        if (Objects.isNull(this.params)) return super.getMessage();
        return MessageFormat.format(super.getMessage(), this.params);
    }
}
