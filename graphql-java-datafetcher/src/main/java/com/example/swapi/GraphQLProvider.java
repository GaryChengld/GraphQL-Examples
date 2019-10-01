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
    @Autowired
    private GraphQLDataFetchers graphQLDataFetchers;
    @Autowired
    private GraphQLTypeResolver graphQLTypeResolver;
    private GraphQL graphQL;

    @PostConstruct
    public void init() throws IOException {
        log.info("init GraphQLProvider");
        URL url = Resources.getResource("swapi.graphql");
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
                        .dataFetcher("about", graphQLDataFetchers.aboutDataFetcher())
                        .dataFetcher("character", graphQLDataFetchers.characterDataFetcher())
                        .dataFetcher("human", graphQLDataFetchers.humanDataFetcher())
                        .dataFetcher("droid", graphQLDataFetchers.droidDataFetcher())
                        .dataFetcher("starship", graphQLDataFetchers.starshipDataFetcher())
                        .dataFetcher("hero", graphQLDataFetchers.heroDataFetcher())
                        .dataFetcher("search", graphQLDataFetchers.searchDataFetcher())
                        .dataFetcher("reviews", graphQLDataFetchers.reviewsDataFetcher()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("createReview", graphQLDataFetchers.createReviewDataFetcher())
                        .dataFetcher("setAboutMessage", graphQLDataFetchers.setAboutMessage()))
                .type(newTypeWiring("Character")
                        .typeResolver(graphQLTypeResolver.characterTypeResolver))
                .type(newTypeWiring("Human")
                        .dataFetcher("friends", graphQLDataFetchers.friendsDataFetcher())
                        .dataFetcher("starships", graphQLDataFetchers.humanStarshipsDataFetcher()))
                .type(newTypeWiring("Droid")
                        .dataFetcher("friends", graphQLDataFetchers.friendsDataFetcher()))
                .type(newTypeWiring("Starship")
                        .dataFetcher("length", graphQLDataFetchers.starshipLengthDataFetcher()))
                .type(newTypeWiring("SearchResult")
                        .typeResolver(graphQLTypeResolver.characterTypeResolver))
                .build();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }
}
