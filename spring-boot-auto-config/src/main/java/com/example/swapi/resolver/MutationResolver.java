package com.example.swapi.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.swapi.data.DataService;
import com.example.swapi.data.Episode;
import com.example.swapi.data.Review;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class MutationResolver implements GraphQLMutationResolver {
    private final DataService dataService;

    public MutationResolver(DataService dataService) {
        this.dataService = dataService;
    }

    public Review createReview(Episode episode, ReviewInput input) {
        Review review = input.toReview();
        return dataService.addReview(episode, review);
    }

    public String setAboutMessage(String message) {
        this.dataService.setAboutMessage(message);
        return message;
    }
}
