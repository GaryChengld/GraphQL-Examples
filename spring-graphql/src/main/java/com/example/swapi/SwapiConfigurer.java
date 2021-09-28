package com.example.swapi;

import graphql.schema.idl.RuntimeWiring;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * @author Gary Cheng
 */
@Configuration
@Slf4j
public class SwapiConfigurer implements RuntimeWiringConfigurer {
    private GraphQLTypeResolver graphQLTypeResolver;

    public SwapiConfigurer(GraphQLTypeResolver graphQLTypeResolver) {
        this.graphQLTypeResolver = graphQLTypeResolver;
    }

    @Override
    public void configure(RuntimeWiring.Builder builder) {
        log.debug("configure RuntimeWiring");
        builder.type(newTypeWiring("Character")
                .typeResolver(graphQLTypeResolver::resolveObject));
        builder.type(newTypeWiring("SearchResult")
                .typeResolver(graphQLTypeResolver::resolveObject));
    }
}
