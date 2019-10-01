package com.example.swapi;

import com.example.swapi.data.*;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public DataFetcher aboutDataFetcher() {
        return dataFetchingEnvironment -> aboutMessage;
    }

    public DataFetcher characterDataFetcher() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return dataService.getCharacter(id);
        };
    }

    public DataFetcher humanDataFetcher() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return dataService.getHuman(id);
        };
    }

    public DataFetcher humanStarshipsDataFetcher() {
        return dataFetchingEnvironment -> {
            Human human = dataFetchingEnvironment.getSource();
            return dataService.getStarshipsByHumanId(human.getId());
        };
    }

    public DataFetcher starshipDataFetcher() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return dataService.getStarship(id);
        };
    }

    public DataFetcher droidDataFetcher() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return dataService.getDroid(id);
        };
    }

    public DataFetcher friendsDataFetcher() {
        return dataFetchingEnvironment -> {
            MovieCharacter character = dataFetchingEnvironment.getSource();
            return dataService.getFriends(character.getId());
        };
    }

    public DataFetcher starshipLengthDataFetcher() {
        return dataFetchingEnvironment -> {
            Starship starship = dataFetchingEnvironment.getSource();
            LengthUnit unit = LengthUnit.valueOf(dataFetchingEnvironment.getArgument("unit"));
            return this.getLength(starship.getLength(), unit);
        };
    }

    public DataFetcher heroDataFetcher() {
        return dataFetchingEnvironment -> {
            Episode episode = Episode.valueOf(dataFetchingEnvironment.getArgument("episode"));
            return dataService.getHero(episode);
        };
    }

    public DataFetcher searchDataFetcher() {
        return dataFetchingEnvironment -> {
            String text = dataFetchingEnvironment.getArgument("text");
            return dataService.search(text);
        };
    }

    public DataFetcher setAboutMessage() {
        return dataFetchingEnvironment -> {
            String message = dataFetchingEnvironment.getArgument("message");
            this.aboutMessage = message;
            return message;
        };
    }

    public DataFetcher createReviewDataFetcher() {
        return dataFetchingEnvironment -> {
            Episode episode = Episode.valueOf(dataFetchingEnvironment.getArgument("episode"));
            Map<String, Object> input = dataFetchingEnvironment.getArgument("review");
            Integer stars = (Integer) input.get("stars");
            String commentary = (String) input.get("commentary");
            Review review = new Review(stars, commentary);
            return dataService.addReview(episode, review);
        };
    }

    public DataFetcher reviewsDataFetcher() {
        return dataFetchingEnvironment -> {
            Episode episode = Episode.valueOf(dataFetchingEnvironment.getArgument("episode"));
            return dataService.getReviews(episode);
        };
    }

    private Double getLength(Double length, LengthUnit unit) {
        if (unit == FOOT) {
            return length * 3.28084;
        } else {
            return length;
        }
    }
}
