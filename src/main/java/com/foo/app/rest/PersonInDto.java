package com.foo.app.rest;

import java.time.LocalDate;

public record PersonInDto(String firstName, String lastName, LocalDate birthDate, String country) {
}
