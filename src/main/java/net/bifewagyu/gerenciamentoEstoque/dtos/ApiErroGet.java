package net.bifewagyu.gerenciamentoEstoque.dtos;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

public record ApiErroGet(HttpStatus status, String message) {

    public ApiErroGet(FieldError fieldError) {
        this(HttpStatus.BAD_REQUEST, fieldError.getDefaultMessage());
    }

}
