package com.example.swapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Gary Cheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Droid implements MovieCharacter {
    private String id;
    private String name;
    private List<String> friends;
    private List<Episode> appearsIn;
    private String primaryFunction;
}
