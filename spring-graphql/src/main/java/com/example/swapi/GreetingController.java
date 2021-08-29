package com.example.swapi;

import org.springframework.graphql.data.method.annotation.GraphQlController;
import org.springframework.graphql.data.method.annotation.QueryMapping;

/**
 * @author Gary Cheng
 */
@GraphQlController
public class GreetingController {
    @QueryMapping
    public String greeting() {
        return "Hello GraphQL";
    }
}
