package com.example.swapi;

import com.example.swapi.data.*;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.example.swapi.data.LengthUnit.FOOT;

/**
 * @author Gary Cheng
 */
@Component
@Slf4j
public class GraphQLDataFetchers {
    private DataService dataService;
    private String aboutMessage = "Star Wars GraphQL api v1.0";

    @Autowired
    public GraphQLDataFetchers(DataService dataService) {
        this.dataService = dataService;
    }

    public CompletableFuture<MovieCharacter> getcharacter(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return CompletableFuture.completedFuture(dataService.getCharacter(id));
    }

    public CompletableFuture<Human> getHuman(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return CompletableFuture.completedFuture(dataService.getHuman(id));
    }

    public CompletableFuture<Droid> getDroid(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return CompletableFuture.completedFuture(dataService.getDroid(id));
    }

    public CompletableFuture<MovieCharacter> getHero(DataFetchingEnvironment environment) {
        Episode episode = Episode.valueOf(environment.getArgument("episode"));
        return CompletableFuture.completedFuture(dataService.getHero(episode));
    }

    public CompletableFuture<List<Starship>> getStarshipsByHuman(DataFetchingEnvironment environment) {
        Human human = environment.getSource();
        return CompletableFuture.completedFuture(dataService.getStarshipsByHumanId(human.getId()));
    }

    public CompletableFuture<Starship> getStarship(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return CompletableFuture.completedFuture(dataService.getStarship(id));
    }

    public CompletableFuture<List<MovieCharacter>> getFriends(DataFetchingEnvironment environment) {
        MovieCharacter character = environment.getSource();
        return CompletableFuture.completedFuture(dataService.getFriends(character.getId()));
    }

    public Double getStarshipLength(DataFetchingEnvironment environment) {
        Starship starship = environment.getSource();
        LengthUnit unit = LengthUnit.valueOf(environment.getArgument("unit"));
        return this.getLength(starship.getLength(), unit);
    }

    public CompletableFuture<List<Object>> search(DataFetchingEnvironment environment) {
        String text = environment.getArgument("text");
        return CompletableFuture.completedFuture(dataService.search(text));
    }

    public String getAboutMessage(DataFetchingEnvironment environment) {
        return this.aboutMessage;
    }

    public String setAboutMessage(DataFetchingEnvironment environment) {
        String message = environment.getArgument("message");
        this.aboutMessage = message;
        return message;
    }

    public CompletableFuture<Review> createReview(DataFetchingEnvironment environment) {
        Episode episode = Episode.valueOf(environment.getArgument("episode"));
        Map<String, Object> input = environment.getArgument("review");
        Integer stars = (Integer) input.get("stars");
        String commentary = (String) input.get("commentary");
        Review review = new Review(stars, commentary);
        return CompletableFuture.completedFuture(dataService.addReview(episode, review));
    }

    public CompletableFuture<List<Review>> getReviews(DataFetchingEnvironment environment) {
        Episode episode = Episode.valueOf(environment.getArgument("episode"));
        return CompletableFuture.completedFuture(dataService.getReviews(episode));
    }

    private Double getLength(Double length, LengthUnit unit) {
        if (unit == FOOT) {
            return length * 3.28084;
        } else {
            return length;
        }
    }
}
