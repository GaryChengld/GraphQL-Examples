package com.example.swapi;

import com.example.swapi.entity.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.example.swapi.entity.LengthUnit.FOOT;

/**
 * @author Gary Cheng
 */
@Controller
public class SwapiController {
    private DataService dataService;

    public SwapiController(DataService dataService) {
        this.dataService = dataService;
    }

    @QueryMapping
    public String greeting() {
        return "Hello GraphQL";
    }

    @QueryMapping
    public MovieCharacter character(@Argument String id) {
        return dataService.getCharacter(id);
    }

    @QueryMapping
    public Human human(@Argument String id) {
        return dataService.getHuman(id);
    }

    @QueryMapping
    public Starship starship(@Argument String id) {
        return dataService.getStarship(id);
    }

    @QueryMapping
    public MovieCharacter hero(@Argument Episode episode) {
        return dataService.getHero(episode);
    }

    @QueryMapping
    public List<Object> search(@Argument String text) {
        return dataService.search(text);
    }

    @QueryMapping
    public List<Review> reviews(@Argument Episode episode) {
        return dataService.getReviews(episode);
    }

    @MutationMapping
    public Review createReview(@Argument Episode episode, @Argument Review reviewInput) {
        return dataService.addReview(episode, reviewInput);
    }

    @SchemaMapping
    public List<MovieCharacter> friends(Human human) {
        return this.dataService.getFriends(human.getId());
    }

    @SchemaMapping
    public Double height(Human human, @Argument LengthUnit unit) {
        return this.getLength(human.getHeight(), unit);
    }

    @SchemaMapping
    public List<Starship> starships(Human human) {
        return this.dataService.getStarshipsByHumanId(human.getId());
    }

    @SchemaMapping
    public List<MovieCharacter> friends(Droid droid) {
        return this.dataService.getFriends(droid.getId());
    }

    @SchemaMapping
    public Double length(Starship starship, @Argument LengthUnit unit) {
        return this.getLength(starship.getLength(), unit);
    }

    private Double getLength(Double length, LengthUnit unit) {
        if (unit == FOOT) {
            return length * 3.28084;
        } else {
            return length;
        }
    }
}
