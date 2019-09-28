package com.example.swapi.data;

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
public class Droid implements Character{
    private String id;
    private String name;
    private List<String> friends;
    private List<Episode> appearsIn;
    private String primaryFunction;
}
