package com.example.swapi;

import com.example.swapi.entity.Human;
import com.example.swapi.entity.MovieCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlController;
import org.springframework.graphql.data.method.annotation.QueryMapping;

/**
 * @author Gary Cheng
 */
@GraphQlController
public class SwapiController {
    @Autowired
    private DataService dataService;

    @QueryMapping
    public String greeting() {
        return "Hello GraphQL";
    }

    @QueryMapping
    public MovieCharacter character(String id) {
        return dataService.getCharacter(id);
    }

    @QueryMapping
    public Human human(@Argument String id) {
        return dataService.getHuman(id);
    }
}
