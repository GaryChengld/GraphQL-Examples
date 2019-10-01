package com.example.swapi;

import com.example.swapi.data.Droid;
import com.example.swapi.data.Human;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class GraphQLTypeResolver {

    TypeResolver characterTypeResolver = environment -> {
        Object object = environment.getObject();
        if (object instanceof Human) {
            return (GraphQLObjectType) environment.getSchema().getType("Human");
        }
        if (object instanceof Droid) {
            return (GraphQLObjectType) environment.getSchema().getType("Droid");
        } else {
            return (GraphQLObjectType) environment.getSchema().getType("Starship");
        }
    };
}
