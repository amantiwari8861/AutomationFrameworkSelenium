package com.training.model;

public record LoginData(
        String username,
        String password,
        String expectedUrl,
        boolean loginExpected
) {
}