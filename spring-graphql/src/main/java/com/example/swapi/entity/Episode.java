package com.example.swapi.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * @author Gary Cheng
 */
public enum Episode {
    NEWHOPE, EMPIRE, JEDI;

    @JsonCreator
    public static Episode fromName(String name) {
        return Arrays.stream(Episode.values())
                .filter(type -> type.name().equals(name))
                .findFirst()
                .orElse(null);
    }
}
