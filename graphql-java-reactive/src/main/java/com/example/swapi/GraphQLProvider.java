package com.example.swapi;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * @author Gary Cheng
 */
@Component
@Configuration
@Slf4j
public class GraphQLProvider {
    private static final String SCHEMA_RESOURCE = "swapi.graphql";
    private GraphQLDataFetchers graphQLDataFetchers;
    private GraphQLTypeResolver graphQLTypeResolver;
    private GraphQL graphQL;

    @Autowired
    public GraphQLProvider(GraphQLDataFetchers graphQLDataFetchers, GraphQLTypeResolver graphQLTypeResolver) {
        this.graphQLDataFetchers = graphQLDataFetchers;
        this.graphQLTypeResolver = graphQLTypeResolver;
    }

    @PostConstruct
    public void init() throws IOException {
        log.debug("init GraphQLProvider");
        URL url = Resources.getResource(SCHEMA_RESOURCE);
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("about", graphQLDataFetchers::getAboutMessage)
                        .dataFetcher("character", graphQLDataFetchers::getcharacter)
                        .dataFetcher("human", graphQLDataFetchers::getHuman)
                        .dataFetcher("droid", graphQLDataFetchers::getDroid)
                        .dataFetcher("starship", graphQLDataFetchers::getStarship)
                        .dataFetcher("hero", graphQLDataFetchers::getHero)
                        .dataFetcher("search", graphQLDataFetchers::search)
                        .dataFetcher("reviews", graphQLDataFetchers::getReviews))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("createReview", graphQLDataFetchers::createReview)
                        .dataFetcher("setAboutMessage", graphQLDataFetchers::setAboutMessage))
                .type(newTypeWiring("Character")
                        .typeResolver(graphQLTypeResolver::resolveObjectType))
                .type(newTypeWiring("Human")
                        .dataFetcher("friends", graphQLDataFetchers::getFriends)
                        .dataFetcher("starships", graphQLDataFetchers::getStarshipsByHuman))
                .type(newTypeWiring("Droid")
                        .dataFetcher("friends", graphQLDataFetchers::getFriends))
                .type(newTypeWiring("Starship")
                        .dataFetcher("length", graphQLDataFetchers::getStarshipLength))
                .type(newTypeWiring("SearchResult")
                        .typeResolver(graphQLTypeResolver::resolveObjectType))
                .build();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }
}
