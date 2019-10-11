package com.example.vertx.graphql.data;

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
public class Human implements MovieCharacter {
    private String id;
    private String name;
    private Double height;
    private Double mass;
    private List<String> friends;
    private List<Episode> appearsIn;
    private List<String> starships;
}
