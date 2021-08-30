package com.example.swapi.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * @author Gary Cheng
 */
public enum LengthUnit {
    METER, FOOT;

    @JsonCreator
    public static LengthUnit fromName(String name) {
        return Arrays.stream(LengthUnit.values())
                .filter(type -> type.name().equals(name))
                .findFirst()
                .orElse(null);
    }
}
