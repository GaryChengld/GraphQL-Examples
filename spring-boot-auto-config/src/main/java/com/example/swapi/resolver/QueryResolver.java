package com.example.swapi.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
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
public class QueryResolver implements GraphQLQueryResolver {
    private final DataService dataService;

    @Autowired
    public QueryResolver(DataService dataService) {
        this.dataService = dataService;
    }

    public MovieCharacter getCharacter(String id) {
        return dataService.getCharacter(id);
    }

    public Human getHuman(String id) {
        return dataService.getHuman(id);
    }

    public Droid getDroid(String id) {
        return dataService.getDroid(id);
    }

    public MovieCharacter getHero(String episode) {
        return dataService.getHero(Episode.valueOf(episode));
    }

    public Starship getStarship(String id) {
        return dataService.getStarship(id);
    }

    public List<Object> search(DataFetchingEnvironment environment) {
        String text = environment.getArgument("text");
        return dataService.search(text);
    }

    public String getAboutMessage() {
        return dataService.getAboutMessage();
    }

    public List<Review> getReviews(DataFetchingEnvironment environment) {
        Episode episode = Episode.valueOf(environment.getArgument("episode"));
        return dataService.getReviews(episode);
    }

}
