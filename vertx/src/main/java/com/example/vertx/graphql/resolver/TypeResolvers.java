package com.example.vertx.graphql.resolver;

import com.example.vertx.graphql.data.Droid;
import com.example.vertx.graphql.data.Human;
import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;

/**
 * @author Gary Cheng
 */
public class TypeResolvers {

    public GraphQLObjectType resolveObjectType(TypeResolutionEnvironment environment) {
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