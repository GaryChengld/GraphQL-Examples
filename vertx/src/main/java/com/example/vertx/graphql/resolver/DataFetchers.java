package com.example.vertx.graphql.resolver;

import com.example.vertx.graphql.data.DataService;
import com.example.vertx.graphql.data.Droid;
import com.example.vertx.graphql.data.Episode;
import com.example.vertx.graphql.data.Human;
import com.example.vertx.graphql.data.LengthUnit;
import com.example.vertx.graphql.data.MovieCharacter;
import com.example.vertx.graphql.data.Review;
import com.example.vertx.graphql.data.Starship;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.example.vertx.graphql.data.LengthUnit.FOOT;

/**
 * @author Gary Cheng
 */
public class DataFetchers {
    private DataService dataService = new DataService();

    public CompletableFuture<MovieCharacter> getCharacter(DataFetchingEnvironment environment) {
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

    public Double getHumanHeight(DataFetchingEnvironment environment) {
        Human human = environment.getSource();
        LengthUnit unit = LengthUnit.valueOf(environment.getArgument("unit"));
        return this.getLength(human.getHeight(), unit);
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

    public CompletableFuture<String> getAboutMessage(DataFetchingEnvironment environment) {
        return CompletableFuture.completedFuture(dataService.getAboutMessage());
    }

    public CompletableFuture<String> setAboutMessage(DataFetchingEnvironment environment) {
        String message = environment.getArgument("message");
        this.dataService.setAboutMessage(message);
        return CompletableFuture.completedFuture(message);
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
