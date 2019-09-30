package com.example.swapi;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class GraphQLDataFetchers {
    private String aboutMessage = "Star Wars GraphQL Example v1.0";

    public DataFetcher getAboutMessage() {
        return dataFetchingEnvironment -> aboutMessage;
    }

    public DataFetcher setAboutMessage() {
        return dataFetchingEnvironment -> {
            String message = dataFetchingEnvironment.getArgument("message");
            this.aboutMessage = message;
            return message;
        };
    }
}
