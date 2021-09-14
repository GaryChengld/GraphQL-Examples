package com.example.swapi;

import com.example.swapi.entity.Droid;
import com.example.swapi.entity.Human;
import com.example.swapi.entity.MovieCharacter;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlController;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.util.List;

/**
 * @author Gary Cheng
 */
@GraphQlController
public class SwapiController {
    private DataService dataService;

    public SwapiController(DataService dataService) {
        this.dataService = dataService;
    }

    @QueryMapping
    public String greeting() {
        return "Hello GraphQL";
    }

    @QueryMapping
    public MovieCharacter character(@Argument String id) {
        return dataService.getCharacter(id);
    }

    @QueryMapping
    public Human human(@Argument String id) {
        return dataService.getHuman(id);
    }

    @SchemaMapping
    public List<MovieCharacter> friends(Human human) {
        return this.dataService.getFriends(human.getId());
    }

    @SchemaMapping
    public List<MovieCharacter> friends(Droid droid) {
        return this.dataService.getFriends(droid.getId());
    }
}
