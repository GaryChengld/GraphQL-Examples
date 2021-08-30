package com.example.swapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gary Cheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Starship {
    private String id;
    private String name;
    private Double length;
}
