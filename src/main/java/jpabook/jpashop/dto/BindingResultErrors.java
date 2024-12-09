package jpabook.jpashop.dto;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class BindingResultErrors {

    private final String message;
    private final Object rejectedValue;
    private final String field;

    public BindingResultErrors(String defaultMessage, String inputValue, String field) {
        this.message = defaultMessage;
        this.rejectedValue = inputValue;
        this.field = field;
    }


    public static List<BindingResultErrors> createBindingResultErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(fieldError -> new BindingResultErrors(
                        fieldError.getDefaultMessage(),
                        Objects.requireNonNull(fieldError.getRejectedValue()).toString(),
                        fieldError.getField()
                ))
                .collect(Collectors.toList());
    }
    /*   return errors.stream()
                .map(fieldError -> new ErrorResponseBindingResult(
                        fieldError.getDefaultMessage(),
                        fieldError.getField(),
                        Objects.requireNonNull(fieldError.getRejectedValue()).toString(),
                        fieldError.getCode()
                ))
                .collect(Collectors.toList());*/
}
