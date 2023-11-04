package com.foo.app.rest;

import java.time.LocalDate;

public record PersonDto( Long id, String firstName, String lastName, LocalDate birthDate, String country) {
}
