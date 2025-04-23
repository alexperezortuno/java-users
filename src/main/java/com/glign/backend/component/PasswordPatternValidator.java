package com.glign.backend.component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PasswordPatternValidator implements ConstraintValidator<PasswordPattern, String> {

    @Value("${regexes.password}")
    private String passwordRegex;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // Maneja @NotBlank por separado
        return Pattern.matches(passwordRegex, value);
    }
}
