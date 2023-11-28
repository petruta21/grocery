package com.tatsiana.grocery.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecaptchaDto {
    private boolean success;
    private List<String> errors;
}