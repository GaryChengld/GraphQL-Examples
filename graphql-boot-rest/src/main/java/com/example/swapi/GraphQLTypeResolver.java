package com.example.swapi;

import com.example.swapi.data.Human;
import com.example.swapi.data.MovieCharacter;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;

/**
 * @author Gary Cheng
 */
public class GraphQLTypeResolver {

    TypeResolver characterTypeResolver = environment -> {
        MovieCharacter character = environment.getObject();
        if (character instanceof Human) {
            return (GraphQLObjectType) environment.getSchema().getType("Human");
        } else {
            return (GraphQLObjectType) environment.getSchema().getType("Droid");
        }
    };
}
