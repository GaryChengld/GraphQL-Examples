package com.example.swapi.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.swapi.data.*;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public MovieCharacter character(String id) {
        return dataService.getCharacter(id);
    }

    public Human human(String id) {
        return dataService.getHuman(id);
    }

    public Droid droid(String id) {
        return dataService.getDroid(id);
    }

    public MovieCharacter hero(Episode episode) {
        return dataService.getHero(episode);
    }

    public Starship starship(String id) {
        return dataService.getStarship(id);
    }

    public List<Object> search(String text) {
        return dataService.search(text);
    }

    public String about() {
        return dataService.getAboutMessage();
    }

    public List<Review> reviews(Episode episode) {
        return dataService.getReviews(episode);
    }

}
