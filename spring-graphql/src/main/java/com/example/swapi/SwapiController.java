package com.example.swapi;

import com.example.swapi.entity.*;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlController;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.util.List;

import static com.example.swapi.entity.LengthUnit.FOOT;

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

    @QueryMapping
    public Starship starship(@Argument String id) {
        return dataService.getStarship(id);
    }

    @SchemaMapping
    public List<MovieCharacter> friends(Human human) {
        return this.dataService.getFriends(human.getId());
    }

    @SchemaMapping
    public Double height(Human human, DataFetchingEnvironment environment) {
        LengthUnit unit = LengthUnit.valueOf(environment.getArgument("unit"));
        return this.getLength(human.getHeight(), unit);
    }

    @SchemaMapping
    public List<Starship> starships(Human human) {
        return this.dataService.getStarshipsByHumanId(human.getId());
    }

    @SchemaMapping
    public List<MovieCharacter> friends(Droid droid) {
        return this.dataService.getFriends(droid.getId());
    }

    @SchemaMapping
    public Double length(Starship starship, DataFetchingEnvironment environment) {
        LengthUnit unit = LengthUnit.valueOf(environment.getArgument("unit"));
        return this.getLength(starship.getLength(), unit);
    }

    private Double getLength(Double length, LengthUnit unit) {
        if (unit == FOOT) {
            return length * 3.28084;
        } else {
            return length;
        }
    }
}
