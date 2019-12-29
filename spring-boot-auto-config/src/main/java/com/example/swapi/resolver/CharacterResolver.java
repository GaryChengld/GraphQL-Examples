package com.example.swapi.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.swapi.data.DataService;
import com.example.swapi.data.MovieCharacter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Gary Cheng
 */
@Component
public class CharacterResolver implements GraphQLResolver<MovieCharacter> {
    private DataService dataService;

    public CharacterResolver(DataService dataService) {
        this.dataService = dataService;
    }

    public List<MovieCharacter> getFriends(MovieCharacter character) {
        return dataService.getFriends(character.getId());
    }
}
