package com.example.swapi;

import com.example.swapi.entity.Droid;
import com.example.swapi.entity.Human;
import com.example.swapi.entity.MovieCharacter;
import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlController;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

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

    @SchemaMapping
    public GraphQLObjectType getType(TypeResolutionEnvironment environment) {
        Object object = environment.getObject();
        if (object instanceof Human) {
            return (GraphQLObjectType) environment.getSchema().getType("Human");
        } else if (object instanceof Droid) {
            return (GraphQLObjectType) environment.getSchema().getType("Droid");
        } else {
            return (GraphQLObjectType) environment.getSchema().getType("Starship");
        }
    }


}
