package com.example.swapi;

import com.example.swapi.entity.Droid;
import com.example.swapi.entity.Human;
import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class GraphQLTypeResolver {

    public GraphQLObjectType resolveObject(TypeResolutionEnvironment environment) {
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