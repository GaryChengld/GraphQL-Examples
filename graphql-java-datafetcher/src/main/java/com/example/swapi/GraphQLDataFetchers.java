package com.example.swapi;

import com.example.swapi.data.DataService;
import com.example.swapi.data.Droid;
import com.example.swapi.data.Episode;
import com.example.swapi.data.Human;
import com.example.swapi.data.LengthUnit;
import com.example.swapi.data.MovieCharacter;
import com.example.swapi.data.Review;
import com.example.swapi.data.Starship;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    public MovieCharacter getcharacter(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return dataService.getCharacter(id);
    }

    public Human getHuman(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return dataService.getHuman(id);
    }

    public Droid getDroid(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return dataService.getDroid(id);
    }

    public MovieCharacter getHero(DataFetchingEnvironment environment) {
        Episode episode = Episode.valueOf(environment.getArgument("episode"));
        return dataService.getHero(episode);
    }

    public List<Starship> getStarshipsByHuman(DataFetchingEnvironment environment) {
        Human human = environment.getSource();
        return dataService.getStarshipsByHumanId(human.getId());
    }

    public Starship getStarship(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return dataService.getStarship(id);
    }

    public List<MovieCharacter> getFriends(DataFetchingEnvironment environment) {
        MovieCharacter character = environment.getSource();
        return dataService.getFriends(character.getId());
    }

    public Double getStarshipLength(DataFetchingEnvironment environment) {
        Starship starship = environment.getSource();
        LengthUnit unit = LengthUnit.valueOf(environment.getArgument("unit"));
        return this.getLength(starship.getLength(), unit);
    }

    public List<Object> search(DataFetchingEnvironment environment) {
        String text = environment.getArgument("text");
        return dataService.search(text);
    }

    public String getAboutMessage(DataFetchingEnvironment environment) {
        return this.aboutMessage;
    }

    public String setAboutMessage(DataFetchingEnvironment environment) {
        String message = environment.getArgument("message");
        this.aboutMessage = message;
        return message;
    }

    public Review createReview(DataFetchingEnvironment environment) {
        Episode episode = Episode.valueOf(environment.getArgument("episode"));
        Map<String, Object> input = environment.getArgument("review");
        Integer stars = (Integer) input.get("stars");
        String commentary = (String) input.get("commentary");
        Review review = new Review(stars, commentary);
        return dataService.addReview(episode, review);
    }

    public List<Review> getReviews(DataFetchingEnvironment environment) {
        Episode episode = Episode.valueOf(environment.getArgument("episode"));
        return dataService.getReviews(episode);
    }

    private Double getLength(Double length, LengthUnit unit) {
        if (unit == FOOT) {
            return length * 3.28084;
        } else {
            return length;
        }
    }
}
