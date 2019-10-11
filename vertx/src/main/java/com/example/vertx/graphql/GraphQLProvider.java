package com.example.vertx.graphql;

import com.example.vertx.graphql.resolver.DataFetchers;
import com.example.vertx.graphql.resolver.TypeResolvers;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.vertx.reactivex.ext.web.handler.graphql.GraphQLHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * @author Gary Cheng
 */
@Slf4j
public class GraphQLProvider {
    private static final String SCHEMA_RESOURCE = "swapi.graphql";

    public static GraphQLHandler createHandler() throws IOException {
        return new GraphQLProvider().init();
    }

    private GraphQLHandler init() throws IOException {
        log.debug("init GraphQL...");
        URL url = Resources.getResource(SCHEMA_RESOURCE);
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();
        return GraphQLHandler.create(graphQL);
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        TypeResolvers typeResolvers = new TypeResolvers();
        DataFetchers dataFetchers = new DataFetchers();
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("about", dataFetchers::getAboutMessage)
                        .dataFetcher("character", dataFetchers::getCharacter)
                        .dataFetcher("human", dataFetchers::getHuman)
                        .dataFetcher("droid", dataFetchers::getDroid)
                        .dataFetcher("starship", dataFetchers::getStarship)
                        .dataFetcher("hero", dataFetchers::getHero)
                        .dataFetcher("search", dataFetchers::search)
                        .dataFetcher("reviews", dataFetchers::getReviews))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("createReview", dataFetchers::createReview)
                        .dataFetcher("setAboutMessage", dataFetchers::setAboutMessage))
                .type(newTypeWiring("Character")
                        .typeResolver(typeResolvers::resolveObjectType))
                .type(newTypeWiring("Human")
                        .dataFetcher("height", dataFetchers::getHumanHeight)
                        .dataFetcher("friends", dataFetchers::getFriends)
                        .dataFetcher("starships", dataFetchers::getStarshipsByHuman))
                .type(newTypeWiring("Droid")
                        .dataFetcher("friends", dataFetchers::getFriends))
                .type(newTypeWiring("Starship")
                        .dataFetcher("length", dataFetchers::getStarshipLength))
                .type(newTypeWiring("SearchResult")
                        .typeResolver(typeResolvers::resolveObjectType))
                .build();
    }
}
